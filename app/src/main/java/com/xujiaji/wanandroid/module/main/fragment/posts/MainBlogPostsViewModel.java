package com.xujiaji.wanandroid.module.main.fragment.posts;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.xujiaji.mvvmquick.lifecycle.SingleLiveEvent;
import com.xujiaji.wanandroid.RefreshLoadViewModel;
import com.xujiaji.wanandroid.base.BaseRefreshViewModel;
import com.xujiaji.wanandroid.model.RefreshLoadModel;
import com.xujiaji.wanandroid.repository.bean.BannerBean;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;
import com.xujiaji.wanandroid.repository.bean.PageBean;
import com.xujiaji.wanandroid.repository.bean.Result;
import com.xujiaji.wanandroid.repository.remote.NetLiveEvent;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/5 23:35
 * description:
 */

public class MainBlogPostsViewModel extends BaseRefreshViewModel<BlogPostBean> implements RefreshLoadViewModel<BlogPostBean>{

    private int mType;
    private int mId;
    private final NetLiveEvent<List<BannerBean>> mBannerData = new NetLiveEvent<>();
    private final SingleLiveEvent<RefreshLoadModel<MutableLiveData<Result<PageBean<BlogPostBean>>>>> mBlogPostsLiveData = new SingleLiveEvent<>();

    @Inject
    public MainBlogPostsViewModel(@NonNull Application application) {
        super(application);
    }

    public void setType(int type) {
        mType = type;
    }

    public void setId(int id) {
        mId = id;
    }

    public NetLiveEvent<List<BannerBean>> getObservableBanners() {
        mBannerData.setValue(net.get().getBanners());
        return mBannerData;
    }
    
    public SingleLiveEvent<RefreshLoadModel<MutableLiveData<Result<PageBean<BlogPostBean>>>>> getObservableBlogPosts() {
        mBlogPostsLiveData.setValue(new RefreshLoadModel<>(net.get().getBlogPosts(UPDATE_INDEX), true));
        return mBlogPostsLiveData;
    }

    public SingleLiveEvent<RefreshLoadModel<MutableLiveData<Result<PageBean<BlogPostBean>>>>> getObservablePostTreeDetailList(int id) {
        mBlogPostsLiveData.setValue(new RefreshLoadModel<>(net.get().getPostTreeDetailList(UPDATE_INDEX, id), true));
        return mBlogPostsLiveData;
    }

    @Override
    public void onListRefresh() {
        if (mType == MainBlogPostsFragment.TYPE_MAIN) {
            mBannerData.setValue(net.get().getBanners());
            mBlogPostsLiveData.setValue(new RefreshLoadModel<>(net.get().getBlogPosts(UPDATE_INDEX), true));
        } else if (mType == MainBlogPostsFragment.TYPE_CATEGORY) {
            mBlogPostsLiveData.setValue(new RefreshLoadModel<>(net.get().getPostTreeDetailList(UPDATE_INDEX, mId), true));
        }

    }

    @Override
    public void onListLoad(int offset) {
        if (mType == MainBlogPostsFragment.TYPE_MAIN) {
            mBlogPostsLiveData.postValue(new RefreshLoadModel<>(net.get().getBlogPosts(offset), false));
        } else if (mType == MainBlogPostsFragment.TYPE_CATEGORY) {
            mBlogPostsLiveData.setValue(new RefreshLoadModel<>(net.get().getPostTreeDetailList(offset, mId), false));
        }
    }

    @Override
    public ObservableList<BlogPostBean> getList() {
        return items;
    }
}
