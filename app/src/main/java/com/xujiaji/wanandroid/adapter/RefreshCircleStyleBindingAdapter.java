package com.xujiaji.wanandroid.adapter;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

import com.xujiaji.wanandroid.R;

/**
 * author: xujiaji
 * created on: 2018/8/19 14:12
 * description: 为SwipeRefreshLayout添加下拉圈圈的风格
 */
public class RefreshCircleStyleBindingAdapter {
    @BindingAdapter("addCircleStyle")
    public static void addCircleStyle(SwipeRefreshLayout refreshLayout, boolean isSet) {
        if (isSet) {
            refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorPrimary);
        }
    }
}
