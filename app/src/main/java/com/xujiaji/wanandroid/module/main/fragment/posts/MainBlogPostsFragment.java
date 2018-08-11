package com.xujiaji.wanandroid.module.main.fragment.posts;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.FragmentMainBlogPostsBinding;
import com.xujiaji.wanandroid.helper.RefreshLoadHelper;
import com.xujiaji.wanandroid.module.read.ReadActivity;
import com.xujiaji.wanandroid.repository.bean.BannerBean;
import com.xujiaji.wanandroid.repository.remote.DataCallbackImp;
import com.youth.banner.Banner;

import java.util.List;

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
    Banner mBanner;

    @Inject
    public MainBlogPostsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter.addHeaderView(mBanner);
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
        viewModel.getObservableBanners().observeData(this, new DataCallbackImp<List<BannerBean>>() {
            @Override
            public void success(List<BannerBean> bean) {
                mBanner.setImages(bean);
                mBanner.start();
            }

        });

        viewModel.getObservableBlogPosts().observe(this, RefreshLoadHelper.listener(this, mAdapter, binding.refresh, viewModel));
        viewModel.mClickEvent.observe(this, blogPostBean -> ReadActivity.launch(getActivity(), blogPostBean));
    }


    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }
}
