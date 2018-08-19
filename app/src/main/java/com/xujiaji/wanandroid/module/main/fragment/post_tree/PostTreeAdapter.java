package com.xujiaji.wanandroid.module.main.fragment.post_tree;

import com.xujiaji.mvvmquick.base.MQQuickAdapter;
import com.xujiaji.mvvmquick.base.MQViewHolder;
import com.xujiaji.mvvmquick.callback.GeneralClickCallback;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.databinding.ItemPostTreeBinding;
import com.xujiaji.wanandroid.repository.bean.TreeBean;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/19 17:18
 * description:
 */
public class PostTreeAdapter extends MQQuickAdapter<TreeBean, ItemPostTreeBinding> {

    @Inject
    PostTreeViewModel mViewModel;

    @Inject
    public PostTreeAdapter() {
        super(R.layout.item_post_tree);
    }

    @Override
    protected void onBinding(ItemPostTreeBinding binding) {
        binding.setCallback((GeneralClickCallback<TreeBean>) mViewModel.mClickEvent::setValue);
        binding.setViewModel(mViewModel);
    }

    @Override
    protected void convert(MQViewHolder<ItemPostTreeBinding> helper, TreeBean item) {
        helper.binding.setPostTree(item);
        helper.binding.executePendingBindings();
    }
}
