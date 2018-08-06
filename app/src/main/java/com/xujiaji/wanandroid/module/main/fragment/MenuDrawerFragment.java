package com.xujiaji.wanandroid.module.main.fragment;

import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.DrawerFragmentMenuBinding;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/4 9:50
 * description:
 */
@ActivityScoped
public class MenuDrawerFragment extends BaseFragment<DrawerFragmentMenuBinding, MQViewModel> {
    @Inject
    public MenuDrawerFragment() {}
}
