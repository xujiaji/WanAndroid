package com.xujiaji.wanandroid.module.main.fragment.friend_link;

import com.xujiaji.mvvmquick.base.MQQuickAdapter;
import com.xujiaji.mvvmquick.base.MQViewHolder;
import com.xujiaji.mvvmquick.callback.GeneralClickCallback;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.databinding.ItemFriendLinkBinding;
import com.xujiaji.wanandroid.repository.bean.FriendLinkBean;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/19 17:18
 * description:
 */
public class FriendLinkAdapter extends MQQuickAdapter<FriendLinkBean, ItemFriendLinkBinding> {

    @Inject
    FriendLinkViewModel mViewModel;

    @Inject
    public FriendLinkAdapter() {
        super(R.layout.item_friend_link);
    }

    @Override
    protected void onBinding(ItemFriendLinkBinding binding) {
        binding.setCallback((GeneralClickCallback<FriendLinkBean>) mViewModel.mClickEvent::setValue);
    }

    @Override
    protected void convert(MQViewHolder<ItemFriendLinkBinding> helper, FriendLinkBean item) {
        helper.binding.setFriendLinkBean(item);
        helper.binding.executePendingBindings();
    }
}
