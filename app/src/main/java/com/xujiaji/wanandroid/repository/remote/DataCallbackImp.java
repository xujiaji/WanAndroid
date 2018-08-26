package com.xujiaji.wanandroid.repository.remote;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.xujiaji.mvvmquick.util.LogUtil;
import com.xujiaji.wanandroid.helper.EmptyViewHelper;
import com.xujiaji.wanandroid.helper.ToastHelper;

import java.lang.ref.WeakReference;

/**
 * author: xujiaji
 * created on: 2018/8/12 0:33
 * description:
 */
public abstract class DataCallbackImp<T> implements DataCallback<T> {

    private boolean isToastErr = true;
    private WeakReference<SwipeRefreshLayout> refreshLayout;

    public DataCallbackImp() {}

    public DataCallbackImp(boolean isToastErr) {
        this.isToastErr = isToastErr;
    }


    public DataCallbackImp(SwipeRefreshLayout refreshLayout) {
        this.refreshLayout = new WeakReference<>(refreshLayout);
    }

    @Override
    public void finished() {
        if (refreshLayout != null && refreshLayout.get() != null) {
            refreshLayout.get().setRefreshing(false);
        }
    }

    @Override
    public void fail(int code, String msg) {
        if (isToastErr && msg != null) {
            ToastHelper.error(msg);
        }

        if (refreshLayout != null && refreshLayout.get() != null) {
            for (int i = 0; i < refreshLayout.get().getChildCount(); i++) {
                if (!(refreshLayout.get().getChildAt(i) instanceof RecyclerView)) continue;
                RecyclerView recyclerView = (RecyclerView) refreshLayout.get().getChildAt(i);
                EmptyViewHelper.setErrEmpty(recyclerView, msg);
                return;
            }
        }
    }
}
