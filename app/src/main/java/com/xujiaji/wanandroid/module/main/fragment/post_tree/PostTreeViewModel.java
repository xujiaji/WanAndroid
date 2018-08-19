package com.xujiaji.wanandroid.module.main.fragment.post_tree;

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
public class PostTreeViewModel extends BaseRefreshViewModel<TreeBean> {

    private NetLiveEvent<List<TreeBean>> mPostTreeData = new NetLiveEvent<>();
    public final SingleLiveEvent<TreeBean> mTagClickEvent = new SingleLiveEvent<>();

    @Inject
    public PostTreeViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onListRefresh() {
        mPostTreeData.setValue(net.get().getPostTree());
    }

    public NetLiveEvent<List<TreeBean>> getObservablePostTree() {
        mPostTreeData.setValue(net.get().getPostTree());
        return mPostTreeData;
    }

}
