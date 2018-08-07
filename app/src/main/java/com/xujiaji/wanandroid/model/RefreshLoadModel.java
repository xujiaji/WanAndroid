package com.xujiaji.wanandroid.model;

import org.jetbrains.annotations.NotNull;

/**
 * author: xujiaji
 * created on: 2018/8/7 12:20
 * description:
 */
public class RefreshLoadModel<T> {

    @NotNull
    public final T data;
    @NotNull
    public final boolean isRefresh;//是不是刷新就是加载

    public RefreshLoadModel(@NotNull T data, @NotNull boolean isRefresh) {
        this.data = data;
        this.isRefresh = isRefresh;
    }
}
