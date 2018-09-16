package com.xujiaji.wanandroid.module.like;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.xujiaji.mvvmquick.lifecycle.SingleLiveEvent;
import com.xujiaji.wanandroid.RefreshLoadViewModel;
import com.xujiaji.wanandroid.base.BaseRefreshViewModel;
import com.xujiaji.wanandroid.model.RefreshLoadModel;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;
import com.xujiaji.wanandroid.repository.bean.PageBean;
import com.xujiaji.wanandroid.repository.bean.Result;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/28 20:05
 * description:
 */
@Singleton
public class LikeViewModel extends BaseRefreshViewModel<BlogPostBean> implements RefreshLoadViewModel<BlogPostBean> {

    private final SingleLiveEvent<RefreshLoadModel<MutableLiveData<Result<PageBean<BlogPostBean>>>>> mCollectsLiveData = new SingleLiveEvent<>();


    @Inject
    public LikeViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onListRefresh() {
        super.onListRefresh();
        mCollectsLiveData.setValue(new RefreshLoadModel<>(net.get().getCollects(UPDATE_INDEX), true));
    }

    @Override
    public void onListLoad(int offset) {
        mCollectsLiveData.postValue(new RefreshLoadModel<>(net.get().getCollects(offset), false));
    }


    public SingleLiveEvent<RefreshLoadModel<MutableLiveData<Result<PageBean<BlogPostBean>>>>> getObservableCollects() {
        mCollectsLiveData.setValue(new RefreshLoadModel<>(net.get().getCollects(UPDATE_INDEX), true));
        return mCollectsLiveData;
    }

    @Override
    public ObservableList<BlogPostBean> getList() {
        return items;
    }
}
