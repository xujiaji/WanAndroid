package com.xujiaji.wanandroid.adapter;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.xujiaji.mvvmquick.util.VersionUtil;
import com.xujiaji.wanandroid.base.App;

/**
 * author: xujiaji
 * created on: 2018/8/29 23:29
 * description:
 */
public class VersionBindingAdapter {

    @BindingAdapter("displayVersion")
    public static void displayVersion(TextView textView, boolean display) {
        if (!display) return;
        textView.setText(String.format("Version %s", VersionUtil.getVersionName(App.getInstance())));
    }

}
