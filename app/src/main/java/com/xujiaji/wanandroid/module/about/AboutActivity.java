package com.xujiaji.wanandroid.module.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xujiaji.mvvmquick.base.NoneViewModel;
import com.xujiaji.wanandroid.base.BaseActivity;
import com.xujiaji.wanandroid.databinding.ActivityAboutBinding;
import com.xujiaji.wanandroid.helper.ToolbarHelper;
import com.xujiaji.wanandroid.module.license.LicenseActivity;

/**
 * author: xujiaji
 * created on: 2018/8/29 22:50
 * description:
 */
public class AboutActivity extends BaseActivity<ActivityAboutBinding, NoneViewModel> {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, AboutActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ToolbarHelper.initTranslucent(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBinding(ActivityAboutBinding binding) {
        super.onBinding(binding);
        ToolbarHelper.initMarginTopDiffBar(binding.btnBack);
        binding.btnBack.setOnClickListener(v -> finish());
        binding.btnLookLibraries.setOnClickListener(v -> LicenseActivity.launch(AboutActivity.this));
    }

}
