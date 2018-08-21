package com.xujiaji.wanandroid.repository.remote;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jventura.pybridge.PyManager;
import com.xujiaji.wanandroid.BuildConfig;
import com.xujiaji.wanandroid.base.App;
import com.xujiaji.wanandroid.repository.bean.BannerBean;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;
import com.xujiaji.wanandroid.repository.bean.BoxBean;
import com.xujiaji.wanandroid.repository.bean.PageBean;
import com.xujiaji.wanandroid.repository.bean.Result;
import com.xujiaji.wanandroid.repository.bean.ThreeAPIBean;
import com.xujiaji.wanandroid.repository.bean.TreeBean;
import com.xujiaji.wanandroid.repository.bean.UserBean;
import com.xujiaji.wanandroid.repository.bean.WebNavBean;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: xujiaji
 * created on: 2018/8/5 23:04
 * description:
 */
@Singleton
public class Net {
    /**
     * request result ok
     */
    public static final int OK = 0;

    public static final String SAVE_USER_LOGIN_KEY = "user/login";
    public static final String SAVE_USER_REGISTER_KEY = "user/register";
    public static final String SET_COOKIE_KEY = "set-cookie";
    public static final String COOKIE_NAME = "Cookie";

    public static final int TIME_OUT_READ = 20;
    public static final int TIME_OUT_CONNECT = 5;

    private API mApi;

    @Inject
    public Net(API api) {
        this.mApi = api;
    }

    /**
     * 请求统一处理
     */
    private <T> MutableLiveData<T> handle(Call<T> call) {
        final MutableLiveData<T> data = new MutableLiveData<>();
        call.enqueue(new NetCallback<>(data));
        return data;
    }

    /**
     * 需要python解析的网页进行统一处理
     */
    @SuppressLint("StaticFieldLeak")
    private <R, T extends Result<R>> MutableLiveData<T> handle(Call<String> call, ParserCallback callback, Type type) {
        final MutableLiveData<T> data = new MutableLiveData<>();

        final AsyncTask<String, Integer, R> asyncTask = new AsyncTask<String, Integer, R>() {
            @Override
            protected R doInBackground(String... strings) {
                return new Gson().fromJson(callback.parser(strings[0]), type);
            }

            @Override
            protected void onPostExecute(R r) {
                Result<R> result = new Result<>();
                result.setData(r);
                data.setValue((T) result);
            }
        };

        handle(call).observeForever(asyncTask::execute);
        return data;
    }

    private interface ParserCallback {
        String parser(String data);
    }


    public MutableLiveData<Result<PageBean<BlogPostBean>>> getBlogPosts(int num) {
        return handle(mApi.getBlogPosts(num));
    }

    public MutableLiveData<Result<PageBean<BlogPostBean>>> getProjects(int num) {
        return handle(mApi.getProjects(num));
    }

    public MutableLiveData<Result<List<BannerBean>>> getBanners() {
        return handle(mApi.getBanners());
    }

    public MutableLiveData<Result<UserBean>> postLogin(String username, String password) {
        return handle(mApi.postLogin(username, password));
    }


    public MutableLiveData<Result<UserBean>> postRegister(String username, String password) {
        return handle(mApi.postRegister(username, password, password));
    }

    public MutableLiveData<Result<String>> postCollect(int id) {
        return handle(mApi.postCollect(id));
    }

    public MutableLiveData<Result<String>> postUncollect(int id) {
        return handle(mApi.postUncollect(id));
    }

    public MutableLiveData<Result<List<ThreeAPIBean>>> getThreeAPIBean() {
        return handle(mApi.getOpenAPIS(),
                data -> PyManager.getInstance(App.getInstance()).parserOPENAPISHtml(data),
                new TypeToken<List<ThreeAPIBean>>(){}.getType());
    }

    public MutableLiveData<Result<List<BoxBean>>> getBoxes() {
        return handle(mApi.getBoxes(BuildConfig.PLUGINS_URL));
    }

    public MutableLiveData<Result<List<TreeBean>>> getPostTree() {
        return handle(mApi.getPostTree());
    }

    public MutableLiveData<Result<List<WebNavBean>>> getWebNavs() {
        return handle(mApi.getWebNavs());
    }
}
