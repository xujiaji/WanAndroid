package com.xujiaji.wanandroid.module.license;

import com.xujiaji.mvvmquick.base.MQQuickAdapter;
import com.xujiaji.mvvmquick.base.MQViewHolder;
import com.xujiaji.mvvmquick.callback.GeneralClickCallback;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.databinding.ItemLicensesBinding;
import com.xujiaji.wanandroid.repository.bean.LicenseBean;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/30 19:50
 * description:
 */
public class LicenseAdapter extends MQQuickAdapter<LicenseBean, ItemLicensesBinding> {

    @Inject
    LicenseViewModel mViewModel;

    @Inject
    public LicenseAdapter() {
        super(R.layout.item_licenses);
    }

    @Override
    protected void onBinding(ItemLicensesBinding binding) {
        binding.setCallback((GeneralClickCallback<LicenseBean>) mViewModel.mClickEvent::setValue);
    }

    @Override
    protected void convert(MQViewHolder<ItemLicensesBinding> helper, LicenseBean item) {
        helper.binding.setLicenseBean(item);
        helper.binding.executePendingBindings();
    }
}
