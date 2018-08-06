package com.xujiaji.wanandroid.module.main.fragment;

import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.FragmentMainToolsBinding;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/5 21:07
 * description:
 */
@ActivityScoped
public class MainToolsFragment extends BaseFragment<FragmentMainToolsBinding, MQViewModel> {
    @Inject
    public MainToolsFragment() {}
}
