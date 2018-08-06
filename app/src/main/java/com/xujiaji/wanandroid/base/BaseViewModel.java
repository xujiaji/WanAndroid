package com.xujiaji.wanandroid.base;

import android.app.Application;
import android.support.annotation.NonNull;

import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.wanandroid.repository.remote.Net;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * author: xujiaji
 * created on: 2018/8/6 10:15
 * description:
 */
public class BaseViewModel extends MQViewModel {
    @Inject
    protected Lazy<Net> net;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
