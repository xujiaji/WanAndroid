package com.xujiaji.wanandroid.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.umeng.analytics.MobclickAgent;
import com.xujiaji.mvvmquick.base.MQFragment;
import com.xujiaji.mvvmquick.base.MQViewModel;

/**
 * author: xujiaji
 * created on: 2018/8/5 21:07
 * description:
 */
public abstract class BaseFragment<B extends ViewDataBinding, VM extends MQViewModel> extends MQFragment<B, VM> {

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }
}
