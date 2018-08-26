package com.xujiaji.wanandroid.module.main.fragment.post_tree;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.LayoutRefreshBinding;
import com.xujiaji.wanandroid.helper.EmptyViewHelper;
import com.xujiaji.wanandroid.helper.ToastHelper;
import com.xujiaji.wanandroid.repository.bean.TreeBean;
import com.xujiaji.wanandroid.repository.remote.DataCallbackImp;

import java.util.List;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/19 17:08
 * description:
 */
@ActivityScoped
public class PostTreeFragment extends BaseFragment<LayoutRefreshBinding, PostTreeViewModel> {

    @Inject
    PostTreeAdapter mAdapter;

    @Inject
    public PostTreeFragment() {}

    @Override
    public void onBinding(LayoutRefreshBinding binding) {
        super.onBinding(binding);
        mAdapter.bindToRecyclerView(binding.list);
        EmptyViewHelper.initEmpty(binding.list);
    }

    @Override
    public void onObserveViewModel(PostTreeViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.setRefreshViewModel(viewModel);
        viewModel.getObservablePostTree().observeData(this, new DataCallbackImp<List<TreeBean>>(binding.refresh) {

            @Override
            public void success(List<TreeBean> bean) {
                viewModel.items.clear();
                viewModel.items.addAll(bean);
            }
        });

        viewModel.mTagClickEvent.observe(this, new Observer<TreeBean>() {
            @Override
            public void onChanged(@Nullable TreeBean treeBean) {
                ToastHelper.info(treeBean.getName());
            }
        });
    }
}
