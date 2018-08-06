package com.xujiaji.wanandroid.module.main.fragment.posts;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.FragmentMainBlogPostsBinding;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;
import com.xujiaji.wanandroid.repository.bean.PageBean;
import com.xujiaji.wanandroid.repository.bean.Result;

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
    public MainBlogPostsFragment() {}

    @Override
    public void onBinding(FragmentMainBlogPostsBinding binding) {
        super.onBinding(binding);
        mAdapter.bindToRecyclerView(binding.list);
        mAdapter.setEmptyView(R.layout.no_item_archived, binding.list);
    }

    @Override
    public void onObserveViewModel(MainBlogPostsViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.setMainBlogPostsViewModel(viewModel);
        viewModel.getObservableBlogPosts().observe(this, new Observer<Result<PageBean<BlogPostBean>>>() {
            @Override
            public void onChanged(@Nullable Result<PageBean<BlogPostBean>> pageBeanResult) {
                viewModel.items.addAll(pageBeanResult.getData().getDatas());
            }
        });
    }
}
