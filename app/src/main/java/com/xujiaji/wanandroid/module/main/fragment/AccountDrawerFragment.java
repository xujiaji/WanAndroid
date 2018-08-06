package com.xujiaji.wanandroid.module.main.fragment;

import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.DrawerFragmentAccountBinding;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/4 9:54
 * description:
 */
@ActivityScoped
public class AccountDrawerFragment extends BaseFragment<DrawerFragmentAccountBinding, MQViewModel> {

    @Inject
    public AccountDrawerFragment() {}
}
