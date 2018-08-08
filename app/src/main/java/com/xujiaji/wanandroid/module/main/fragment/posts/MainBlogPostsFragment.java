package com.xujiaji.wanandroid.module.main.fragment.posts;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.FragmentMainBlogPostsBinding;
import com.xujiaji.wanandroid.helper.RefreshLoadHelper;
import com.xujiaji.wanandroid.module.read.ReadActivity;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/5 20:53
 * description:
 */
@ActivityScoped
public class MainBlogPostsFragment extends BaseFragment<FragmentMainBlogPostsBinding, MainBlogPostsViewModel> {

    @Inject
    MainBlogPostsAdapter mAdapter;

    @Inject
    public MainBlogPostsFragment() {
    }

    @Override
    public void onBinding(FragmentMainBlogPostsBinding binding) {
        super.onBinding(binding);
        RefreshLoadHelper.init(binding.refresh, mAdapter, binding.list);
    }

    @Override
    public void onObserveViewModel(MainBlogPostsViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.setMainBlogPostsViewModel(viewModel);
        viewModel.getObservableBlogPosts().observe(this, RefreshLoadHelper.listener(this, mAdapter, binding.refresh, viewModel));
        viewModel.mClickEvent.observe(this, new Observer<BlogPostBean>() {
            @Override
            public void onChanged(@Nullable BlogPostBean blogPostBean) {
                ReadActivity.launch(getActivity(), blogPostBean);
            }
        });
    }
}
