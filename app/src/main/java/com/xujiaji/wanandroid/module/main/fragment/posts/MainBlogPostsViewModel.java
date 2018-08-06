package com.xujiaji.wanandroid.module.main.fragment.posts;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.xujiaji.mvvmquick.lifecycle.SingleLiveEvent;
import com.xujiaji.wanandroid.base.BaseViewModel;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;
import com.xujiaji.wanandroid.repository.bean.PageBean;
import com.xujiaji.wanandroid.repository.bean.Result;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/5 23:35
 * description:
 */
@Singleton
public class MainBlogPostsViewModel extends BaseViewModel {

    public final SingleLiveEvent<BlogPostBean> mClickEvent = new SingleLiveEvent<>();
    public final ObservableList<BlogPostBean> items = new ObservableArrayList<>();

    @Inject
    public MainBlogPostsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Result<PageBean<BlogPostBean>>> getObservableBlogPosts() {
        return net.get().getBlogPosts(0);
    }
}
