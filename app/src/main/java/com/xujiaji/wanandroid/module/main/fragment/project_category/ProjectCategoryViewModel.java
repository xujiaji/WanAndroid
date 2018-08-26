package com.xujiaji.wanandroid.module.main.fragment.project_category;

import android.app.Application;
import android.support.annotation.NonNull;

import com.xujiaji.mvvmquick.lifecycle.SingleLiveEvent;
import com.xujiaji.wanandroid.base.BaseRefreshViewModel;
import com.xujiaji.wanandroid.repository.bean.TreeBean;
import com.xujiaji.wanandroid.repository.remote.NetLiveEvent;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/19 17:08
 * description:
 */
@Singleton
public class ProjectCategoryViewModel extends BaseRefreshViewModel<TreeBean> {

    private NetLiveEvent<List<TreeBean>> mProjectCategoryData = new NetLiveEvent<>();

    @Inject
    public ProjectCategoryViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onListRefresh() {
        mProjectCategoryData.setValue(net.get().getProjectTree());
    }

    public NetLiveEvent<List<TreeBean>> getObservableProjectTree() {
        mProjectCategoryData.setValue(net.get().getProjectTree());
        return mProjectCategoryData;
    }

}
