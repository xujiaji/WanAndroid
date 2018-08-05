package com.xujiaji.wanandroid.module.main.fragment;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;
import com.xujiaji.wanandroid.repository.bean.PageBean;
import com.xujiaji.wanandroid.repository.bean.Result;
import com.xujiaji.wanandroid.repository.remote.NET;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/5 23:35
 * description:
 */
public class MainBlogPostsViewModel extends MQViewModel{
    @Inject
    NET net;

    @Inject
    public MainBlogPostsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Result<PageBean<BlogPostBean>>> getObservableBlogPosts() {
        return net.getBlogPosts(0);
    }
}
