package com.xujiaji.wanandroid.module.main.fragment.boxes;

import com.xujiaji.mvvmquick.base.MQQuickAdapter;
import com.xujiaji.mvvmquick.base.MQViewHolder;
import com.xujiaji.mvvmquick.callback.GeneralClickCallback;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.databinding.ItemBoxBinding;
import com.xujiaji.wanandroid.repository.bean.ToolBean;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/15 10:46
 * description:
 */
public class MainBoxesAdapter extends MQQuickAdapter<ToolBean, ItemBoxBinding> {

    private MainBoxesViewModel mViewModel;

    @Inject
    public MainBoxesAdapter(MainBoxesViewModel viewModel) {
        super(R.layout.item_box);
        this.mViewModel = viewModel;
    }

    @Override
    protected void onBinding(ItemBoxBinding binding) {
        binding.setCallback((GeneralClickCallback<ToolBean>) mViewModel.mClickEvent::setValue);
    }

    @Override
    protected void convert(MQViewHolder<ItemBoxBinding> helper, ToolBean item) {
        helper.binding.setToolBean(item);
        helper.binding.executePendingBindings();
    }
}
