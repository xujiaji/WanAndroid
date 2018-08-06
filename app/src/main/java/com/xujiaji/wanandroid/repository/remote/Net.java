package com.xujiaji.wanandroid.repository.remote;

import android.arch.lifecycle.MutableLiveData;

import com.xujiaji.mvvmquick.callback.NetCallback;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;
import com.xujiaji.wanandroid.repository.bean.PageBean;
import com.xujiaji.wanandroid.repository.bean.Result;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

/**
 * author: xujiaji
 * created on: 2018/8/5 23:04
 * description:
 */
@Singleton
public class Net {
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
}
