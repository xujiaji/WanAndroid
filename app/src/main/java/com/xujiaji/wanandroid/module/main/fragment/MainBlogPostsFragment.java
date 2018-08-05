package com.xujiaji.wanandroid.module.main.fragment;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.FragmentMainBlogPostsBinding;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;
import com.xujiaji.wanandroid.repository.bean.PageBean;
import com.xujiaji.wanandroid.repository.bean.Result;

/**
 * author: xujiaji
 * created on: 2018/8/5 20:53
 * description:
 */
@ActivityScoped
public class MainBlogPostsFragment extends BaseFragment<FragmentMainBlogPostsBinding, MainBlogPostsViewModel> {

    @Override
    public void onObserveViewModel(MainBlogPostsViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        viewModel.getObservableBlogPosts().observe(this, new Observer<Result<PageBean<BlogPostBean>>>() {
            @Override
            public void onChanged(@Nullable Result<PageBean<BlogPostBean>> pageBeanResult) {

            }
        });
    }
}
