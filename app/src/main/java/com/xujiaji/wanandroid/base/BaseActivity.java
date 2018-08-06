package com.xujiaji.wanandroid.base;

import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ViewDataBinding;

import com.xujiaji.mvvmquick.base.MQActivity;

/**
 * author: xujiaji
 * created on: 2018/8/6 11:01
 * description:
 */
public abstract class BaseActivity<B extends ViewDataBinding, VM extends AndroidViewModel> extends MQActivity<B, VM> {
}
