package com.xujiaji.wanandroid.module.license;

import android.app.Application;
import android.support.annotation.NonNull;

import com.xujiaji.wanandroid.base.BaseRefreshViewModel;
import com.xujiaji.wanandroid.repository.bean.LicenseBean;
import com.xujiaji.wanandroid.repository.remote.NetLiveEvent;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/30 19:35
 * description:
 */
@Singleton
public class LicenseViewModel extends BaseRefreshViewModel<LicenseBean> {

    private final NetLiveEvent<List<LicenseBean>> mLicensesData = new NetLiveEvent<>();

    @Inject
    public LicenseViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onListRefresh() {
        mLicensesData.setValue(net.get().getLicenses());
    }

    public NetLiveEvent<List<LicenseBean>> getObservableLicensesData() {
        mLicensesData.setValue(net.get().getLicenses());
        return mLicensesData;
    }

}
