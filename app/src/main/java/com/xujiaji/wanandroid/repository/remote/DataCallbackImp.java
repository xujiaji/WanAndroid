package com.xujiaji.wanandroid.repository.remote;

import com.xujiaji.wanandroid.helper.ToastHelper;

/**
 * author: xujiaji
 * created on: 2018/8/12 0:33
 * description:
 */
public abstract class DataCallbackImp<T> implements DataCallback<T> {

    private boolean isToastErr = true;

    public DataCallbackImp() {}

    public DataCallbackImp(boolean isToastErr) {
        this.isToastErr = isToastErr;
    }

    @Override
    public void finished() {

    }

    @Override
    public void fail(int code, String msg) {
        if (isToastErr && msg != null) {
            ToastHelper.error(msg);
        }
    }
}
