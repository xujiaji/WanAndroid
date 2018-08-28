package com.xujiaji.wanandroid.base;

import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ViewDataBinding;

import com.umeng.analytics.MobclickAgent;
import com.xujiaji.mvvmquick.base.MQActivity;

/**
 * author: xujiaji
 * created on: 2018/8/6 11:01
 * description:
 */
public abstract class BaseActivity<B extends ViewDataBinding, VM extends AndroidViewModel> extends MQActivity<B, VM> {

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
