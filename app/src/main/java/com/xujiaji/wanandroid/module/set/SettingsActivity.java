package com.xujiaji.wanandroid.module.set;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.qihoo360.replugin.RePlugin;
import com.xujiaji.mvvmquick.base.NoneViewModel;
import com.xujiaji.wanandroid.base.BaseActivity;
import com.xujiaji.wanandroid.databinding.ActivitySettingsBinding;
import com.xujiaji.wanandroid.helper.ToolbarHelper;
import com.xujiaji.wanandroid.util.FileUtil;

/**
 * author: xujiaji
 * created on: 2018/8/23 19:39
 * description:
 */
public class SettingsActivity extends BaseActivity<ActivitySettingsBinding, SettingsViewModel> {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, SettingsActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ToolbarHelper.initTranslucent(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBinding(@NonNull ActivitySettingsBinding binding) {
        ToolbarHelper.initMarginTopDiffBar(binding.btnBack);
        binding.btnBack.setOnClickListener(v -> finish());
    }

    @Override
    public void onObserveViewModel(@NonNull SettingsViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.setSettingsViewModel(viewModel);
        viewModel.cacheSize.set(FileUtil.getCacheSizeStr(this));
        viewModel.pluginNum.set(RePlugin.getPluginInfoList().size());
    }
}
