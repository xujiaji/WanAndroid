package com.xujiaji.wanandroid.module.main.fragment.projects;

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
 * created on: 2018/8/5 23:35
 * description:
 */
@Singleton
public class MainProjectsViewModel extends BaseRefreshViewModel<BlogPostBean> implements RefreshLoadViewModel<BlogPostBean>{

    public final SingleLiveEvent<BlogPostBean> mClickEvent = new SingleLiveEvent<>();
    public final SingleLiveEvent<RefreshLoadModel<MutableLiveData<Result<PageBean<BlogPostBean>>>>> projectsLiveData = new SingleLiveEvent<>();

    @Inject
    public MainProjectsViewModel(@NonNull Application application) {
        super(application);
    }

    public SingleLiveEvent<RefreshLoadModel<MutableLiveData<Result<PageBean<BlogPostBean>>>>> getObservableProjects() {
        projectsLiveData.setValue(new RefreshLoadModel<>(net.get().getProjects(UPDATE_INDEX), true));
        return projectsLiveData;
    }

    @Override
    public void onListRefresh() {
        projectsLiveData.setValue(new RefreshLoadModel<>(net.get().getProjects(UPDATE_INDEX), true));
    }

    @Override
    public void onListLoad(int offset) {
        projectsLiveData.postValue(new RefreshLoadModel<>(net.get().getProjects(offset), false));
    }

    @Override
    public ObservableList<BlogPostBean> getList() {
        return items;
    }
}
