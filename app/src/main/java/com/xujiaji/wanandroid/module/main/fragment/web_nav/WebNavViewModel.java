package com.xujiaji.wanandroid.module.main.fragment.web_nav;

import android.app.Application;
import android.support.annotation.NonNull;

import com.xujiaji.mvvmquick.lifecycle.SingleLiveEvent;
import com.xujiaji.wanandroid.base.BaseRefreshClickChildViewModel;
import com.xujiaji.wanandroid.base.BaseRefreshViewModel;
import com.xujiaji.wanandroid.repository.bean.ThreeAPIBean;
import com.xujiaji.wanandroid.repository.bean.WebNavBean;
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
public class WebNavViewModel extends BaseRefreshClickChildViewModel<WebNavBean, ThreeAPIBean.LinkBean> {

    private final NetLiveEvent<List<WebNavBean>> mWebNavListData = new NetLiveEvent<>();

    @Inject
    public WebNavViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onListRefresh() {
        mWebNavListData.setValue(net.get().getWebNavs());
    }

    public NetLiveEvent<List<WebNavBean>> getObservableWebNavs() {
        mWebNavListData.setValue(net.get().getWebNavs());
        return mWebNavListData;
    }
}
