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
 * description:
 */
public class BaseRefreshViewModel<T> extends BaseViewModel {

    public final ObservableList<T> items = new ObservableArrayList<>();
    public final SingleLiveEvent<T> mClickEvent = new SingleLiveEvent<>();
    /**
     * 更新时的第一页页码
     */
    public static final int UPDATE_INDEX = 0;
    /**
     * 加载初始偏移度
     */
    public static final int INIT_LOAD_OFFSET = 0;

    public BaseRefreshViewModel(@NonNull Application application) {
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
