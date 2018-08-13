package com.jventura.pybridge;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class PyManager {

    private static PyManager instance;
    private String pythonPath;

    private PyManager(Context context) {
        // Extract python files from assets
        AssetExtractor assetExtractor = new AssetExtractor(context);
        assetExtractor.removeAssets("python");
        assetExtractor.copyAssets("python");
        // Get the extracted assets directory
        pythonPath = assetExtractor.getAssetsDataDir() + "python";
    }

    public static PyManager getInstance(Context context) {
        if (instance == null) {
            synchronized (PyManager.class) {
                instance = new PyManager(context);
            }
        }
        return instance;
    }

    public String parserOPENAPISHtml(String data) {


        // Start the Python interpreter
        PyBridge.start(pythonPath);

        // Call a Python function
        try {
            JSONObject json = new JSONObject();
            json.put("function", "parserAPISPage");
            json.put("data", data);

            JSONObject result = PyBridge.call(json);
            return result.getString("result");

//            TextView textView = (TextView) findViewById(R.id.textView);
//            textView.setText(answer);

        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        } finally {
            // Stop the interpreter
            PyBridge.stop();
        }
    }
}
