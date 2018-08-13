package com.xujiaji.wanandroid.repository.remote;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jventura.pybridge.PyManager;
import com.xujiaji.mvvmquick.util.LogUtil;
import com.xujiaji.wanandroid.base.App;
import com.xujiaji.wanandroid.helper.ToastHelper;
import com.xujiaji.wanandroid.repository.bean.BannerBean;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;
import com.xujiaji.wanandroid.repository.bean.PageBean;
import com.xujiaji.wanandroid.repository.bean.Result;
import com.xujiaji.wanandroid.repository.bean.ThreeAPIBean;
import com.xujiaji.wanandroid.repository.bean.UserBean;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Response;

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

    private <T> MutableLiveData<T> handle(Call<T> call) {
        final MutableLiveData<T> data = new MutableLiveData<>();
        call.enqueue(new NetCallback<>(data));
        return data;
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

    public MutableLiveData<Result<List<ThreeAPIBean>>> getThreeAPIBean() {
        final MutableLiveData<Result<List<ThreeAPIBean>>> data = new MutableLiveData<>();
        handle(mApi.getOpenAPIS()).observeForever(s -> new Thread() {
            @Override
            public void run() {
                Result<List<ThreeAPIBean>> result = new Result<>();
                result.setData(new Gson().fromJson(PyManager.getInstance(App.getInstance()).parserOPENAPISHtml(s),
                        new TypeToken<List<ThreeAPIBean>>(){}.getType()));
                data.setValue(result);
            }
        }.start());
        return data;
    }

}
