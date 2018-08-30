package com.xujiaji.wanandroid.module.license;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseActivity;
import com.xujiaji.wanandroid.databinding.ActivityLicenseBinding;
import com.xujiaji.wanandroid.helper.EmptyViewHelper;
import com.xujiaji.wanandroid.helper.ToolbarHelper;
import com.xujiaji.wanandroid.module.read.ReadActivity;
import com.xujiaji.wanandroid.repository.bean.LicenseBean;
import com.xujiaji.wanandroid.repository.remote.DataCallbackImp;

import java.util.List;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/30 19:28
 * description:
 */
public class LicenseActivity extends BaseActivity<ActivityLicenseBinding, LicenseViewModel> {

    @Inject
    LicenseAdapter mAdapter;

    public static void launch(@Nullable Context context) {
        if (context == null) return;
        context.startActivity(new Intent(context, LicenseActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ToolbarHelper.initTranslucent(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBinding(ActivityLicenseBinding binding) {
        super.onBinding(binding);
        mAdapter.bindToRecyclerView(binding.layoutRefresh.list);
        EmptyViewHelper.initEmpty(binding.layoutRefresh.list);
        binding.includeBar.toolbar.setTitle(R.string.opean_source_libraries);
        ToolbarHelper.initFullBar(binding.includeBar.toolbar, this);
    }

    @Override
    public void onObserveViewModel(LicenseViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.layoutRefresh.setRefreshViewModel(viewModel);
        viewModel.mClickEvent.observe(this, licenseBean -> ReadActivity.launch(LicenseActivity.this, licenseBean.getName(), licenseBean.getLink()));
        viewModel.getObservableLicensesData().observeData(this, new DataCallbackImp<List<LicenseBean>>(binding.layoutRefresh.refresh) {
            @Override
            public void success(List<LicenseBean> bean) {
                viewModel.items.clear();
                viewModel.items.addAll(bean);
            }
        });
    }
}
