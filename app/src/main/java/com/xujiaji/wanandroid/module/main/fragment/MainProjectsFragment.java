package com.xujiaji.wanandroid.module.main.fragment;

import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.FragmentMainProjectsBinding;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/5 21:06
 * description:
 */
@ActivityScoped
public class MainProjectsFragment extends BaseFragment<FragmentMainProjectsBinding, MQViewModel> {

    @Inject
    public MainProjectsFragment() {}
}
