package com.xujiaji.wanandroid.module.set;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xujiaji.mvvmquick.base.NoneViewModel;
import com.xujiaji.wanandroid.base.BaseActivity;
import com.xujiaji.wanandroid.databinding.ActivitySettingsBinding;
import com.xujiaji.wanandroid.helper.ToolbarHelper;

/**
 * author: xujiaji
 * created on: 2018/8/23 19:39
 * description:
 */
public class SettingsActivity extends BaseActivity<ActivitySettingsBinding, NoneViewModel> {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, SettingsActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ToolbarHelper.initTranslucent(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBinding(ActivitySettingsBinding binding) {
        super.onBinding(binding);
        ToolbarHelper.initMarginTopDiffBar(binding.btnBack);
        binding.btnBack.setOnClickListener(v -> finish());
    }
}
