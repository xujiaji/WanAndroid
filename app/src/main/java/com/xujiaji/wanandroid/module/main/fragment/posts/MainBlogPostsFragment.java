package com.xujiaji.wanandroid.module.main.fragment.posts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.LayoutRefreshBinding;
import com.xujiaji.wanandroid.helper.ActivityResultHelper;
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
public class MainBlogPostsFragment extends BaseFragment<LayoutRefreshBinding, MainBlogPostsViewModel> {

    @Inject
    MainBlogPostsAdapter mAdapter;

    @Inject
    Banner mBanner;

    private List<BannerBean> bannerBeanList;

    @Inject
    public MainBlogPostsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter.addHeaderView(mBanner);
        mBanner.setOnBannerListener(position -> ReadActivity.launch(MainBlogPostsFragment.this, bannerBeanList.get(position).getTitle(), bannerBeanList.get(position).getUrl()));
    }

    @Override
    public void onBinding(LayoutRefreshBinding binding) {
        super.onBinding(binding);
        RefreshLoadHelper.init(binding.refresh, mAdapter, binding.list);
    }

    @Override
    public void onObserveViewModel(MainBlogPostsViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.setRefreshViewModel(viewModel);
        viewModel.getObservableBanners().observeData(this, new DataCallbackImp<List<BannerBean>>() {
            @Override
            public void success(List<BannerBean> bean) {
                bannerBeanList = bean;
                mBanner.setImages(bean);
                mBanner.start();
            }

        });

        viewModel.getObservableBlogPosts().observe(this, RefreshLoadHelper.listener(this, mAdapter, binding.refresh, viewModel));
        viewModel.mClickEvent.observe(this, blogPostBean -> ReadActivity.launch(this, blogPostBean));
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResultHelper.handlePost(requestCode, resultCode, data, mAdapter);
    }
}
