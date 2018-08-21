package com.xujiaji.wanandroid.base;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.xujiaji.mvvmquick.lifecycle.SingleLiveEvent;
import com.xujiaji.wanandroid.repository.remote.Net;

/**
 * author: xujiaji
 * created on: 2018/8/18 22:15
 * description: 点击事件触发的不是一级item的情况下
 */
public class BaseRefreshClickChildViewModel<T, CLICK> extends BaseRefreshViewModel<T> {

    public final SingleLiveEvent<CLICK> mClickChildEvent = new SingleLiveEvent<>();

    public BaseRefreshClickChildViewModel(@NonNull Application application) {
        super(application);
    }
}
