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

    /**
     * 更新时的第一页页码
     */
    public static final int UPDATE_INDEX = 0;
    /**
     * 加载初始偏移度
     */
    public static final int INIT_LOAD_OFFSET = 0;

    @Inject
    protected Lazy<Net> net;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public int timeout() {
        return Net.TIME_OUT_READ;
    }

    @Override
    public int initLoadOffset() {
        return INIT_LOAD_OFFSET;
    }
}
