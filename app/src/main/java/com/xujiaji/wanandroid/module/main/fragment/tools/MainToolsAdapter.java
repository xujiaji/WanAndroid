package com.xujiaji.wanandroid.module.main.fragment.tools;

import com.xujiaji.mvvmquick.base.MQQuickAdapter;
import com.xujiaji.mvvmquick.base.MQViewHolder;
import com.xujiaji.mvvmquick.callback.GeneralClickCallback;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.databinding.ItemToolBinding;
import com.xujiaji.wanandroid.repository.bean.ToolBean;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/15 10:46
 * description:
 */
public class MainToolsAdapter extends MQQuickAdapter<ToolBean, ItemToolBinding> {

    private MainToolsViewModel mViewModel;

    @Inject
    public MainToolsAdapter(MainToolsViewModel viewModel) {
        super(R.layout.item_tool);
        this.mViewModel = viewModel;
    }

    @Override
    protected void onBinding(ItemToolBinding binding) {
        binding.setCallback((GeneralClickCallback<ToolBean>) mViewModel.mClickEvent::setValue);
    }

    @Override
    protected void convert(MQViewHolder<ItemToolBinding> helper, ToolBean item) {
        helper.binding.setToolBean(item);
        helper.binding.executePendingBindings();
    }
}
