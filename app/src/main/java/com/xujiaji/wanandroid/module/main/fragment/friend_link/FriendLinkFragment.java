package com.xujiaji.wanandroid.module.main.fragment.friend_link;

import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;

import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.LayoutRefreshBinding;
import com.xujiaji.wanandroid.module.read.ReadActivity;
import com.xujiaji.wanandroid.repository.bean.FriendLinkBean;
import com.xujiaji.wanandroid.repository.remote.DataCallbackImp;

import java.util.List;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/22 17:22
 * description:
 */
@ActivityScoped
public class FriendLinkFragment extends BaseFragment<LayoutRefreshBinding, FriendLinkViewModel> {

    @Inject
    FriendLinkAdapter mAdapter;

    @Inject
    public FriendLinkFragment() {}

    @Override
    public void onObserveViewModel(FriendLinkViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.setRefreshViewModel(viewModel);
        binding.list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mAdapter.bindToRecyclerView(binding.list);
        mAdapter.setEmptyView(R.layout.no_item_archived, binding.list);

        viewModel.getObservableFriendLinksData().observeData(this, new DataCallbackImp<List<FriendLinkBean>>() {
            @Override
            public void success(List<FriendLinkBean> bean) {
                viewModel.items.clear();
                viewModel.items.addAll(bean);
            }
        });

        viewModel.mClickEvent.observe(this, friendLinkBean -> ReadActivity.launch(FriendLinkFragment.this, TextUtils.isEmpty(friendLinkBean.getTitle()) ? friendLinkBean.getUrl() : friendLinkBean.getTitle(), friendLinkBean.getUrl()));
    }
}
