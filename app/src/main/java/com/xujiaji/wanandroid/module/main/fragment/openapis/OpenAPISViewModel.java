package com.xujiaji.wanandroid.module.main.fragment.openapis;

import android.app.Application;
import android.support.annotation.NonNull;

import com.xujiaji.mvvmquick.lifecycle.SingleLiveEvent;
import com.xujiaji.wanandroid.base.BaseRefreshViewModel;
import com.xujiaji.wanandroid.repository.bean.ThreeAPIBean;
import com.xujiaji.wanandroid.repository.remote.NetLiveEvent;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/18 22:47
 * description:
 */
@Singleton
public class OpenAPISViewModel extends BaseRefreshViewModel<ThreeAPIBean> {

    private final NetLiveEvent<List<ThreeAPIBean>> mThreeAPIListData = new NetLiveEvent<>();
    public final SingleLiveEvent<ThreeAPIBean.LinkBean> mClickEvent = new SingleLiveEvent<>();

    @Inject
    public OpenAPISViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onListRefresh() {
        mThreeAPIListData.setValue(net.get().getThreeAPIBean());
    }

    public NetLiveEvent<List<ThreeAPIBean>> getObservableThreeAPIS() {
        mThreeAPIListData.setValue(net.get().getThreeAPIBean());
        return mThreeAPIListData;
    }
}
