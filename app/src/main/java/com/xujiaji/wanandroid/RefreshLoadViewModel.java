package com.xujiaji.wanandroid;

import android.databinding.ObservableList;

/**
 * author: xujiaji
 * created on: 2018/8/7 14:04
 * description:
 */
public interface RefreshLoadViewModel<T> {
    public ObservableList<T> getList();
}
