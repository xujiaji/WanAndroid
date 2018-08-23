package com.xujiaji.wanandroid.helper;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xujiaji.mvvmquick.util.ScreenUtils;
import com.xujiaji.wanandroid.base.BaseActivity;
import com.xujiaji.wanandroid.util.StatusBarUtil;

/**
 * author: xujiaji
 * created on: 2018/8/7 22:11
 * description:
 */
public class ToolbarHelper {

    public static void initTranslucent(BaseActivity activity) {
        StatusBarUtil.setTranslucentStatus(activity);
        StatusBarUtil.setLightMode(activity);
    }

    public static void initMarginTopDiffBar(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) params).topMargin = ScreenUtils.getStatusHeight(view.getContext());
        }
        view.setLayoutParams(params);
    }

    public static void initFullBar(Toolbar toolbar, BaseActivity activity) {
        ViewGroup.LayoutParams params = toolbar.getLayoutParams();
        params.height = ScreenUtils.getStatusHeight(activity) + ViewHelper.getSystemActionBarSize(activity);
        toolbar.setLayoutParams(params);
        toolbar.setPadding(
                toolbar.getLeft(),
                toolbar.getTop() + ScreenUtils.getStatusHeight(activity),
                toolbar.getRight(),
                toolbar.getBottom()
        );
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }
}
