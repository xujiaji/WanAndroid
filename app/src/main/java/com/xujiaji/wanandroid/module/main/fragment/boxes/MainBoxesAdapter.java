package com.xujiaji.wanandroid.module.main.fragment.boxes;

import com.xujiaji.mvvmquick.base.MQQuickAdapter;
import com.xujiaji.mvvmquick.base.MQViewHolder;
import com.xujiaji.mvvmquick.callback.GeneralClickCallback;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.databinding.ItemBoxBinding;
import com.xujiaji.wanandroid.repository.bean.BoxBean;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/15 10:46
 * description:
 */
public class MainBoxesAdapter extends MQQuickAdapter<BoxBean, ItemBoxBinding> {

    private MainBoxesViewModel mViewModel;

    @Inject
    public MainBoxesAdapter(MainBoxesViewModel viewModel) {
        super(R.layout.item_box);
        this.mViewModel = viewModel;
    }

    @Override
    protected void onBinding(ItemBoxBinding binding) {
        binding.setCallback((GeneralClickCallback<BoxBean>) mViewModel.mClickEvent::setValue);
        binding.setClickGithubCallback((GeneralClickCallback<BoxBean>) mViewModel.mClickGithubEvent::setValue);
    }

    @Override
    protected void convert(MQViewHolder<ItemBoxBinding> helper, BoxBean item) {
        helper.binding.setBoxBean(item);
        helper.binding.executePendingBindings();
    }
}
