package com.xujiaji.wanandroid.model;

import com.xujiaji.wanandroid.base.BaseFragment;

/**
 * author: xujiaji
 * created on: 2018/8/4 0:13
 * description:
 */
public class FragmentModel {
    private String title;
    private BaseFragment fragment;

    public FragmentModel(String title, BaseFragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public BaseFragment getFragment() {
        return fragment;
    }

}
