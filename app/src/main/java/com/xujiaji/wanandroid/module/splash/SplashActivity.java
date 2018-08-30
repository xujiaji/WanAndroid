package com.xujiaji.wanandroid.module.splash;

import android.app.Activity;
import android.content.Intent;

import com.xujiaji.wanandroid.module.main.MainActivity;

public class SplashActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}