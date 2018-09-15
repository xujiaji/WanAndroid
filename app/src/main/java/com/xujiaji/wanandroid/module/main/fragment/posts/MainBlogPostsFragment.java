package com.xujiaji.wanandroid.module.main.fragment.posts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import java.util.Optional;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * author: xujiaji
 * created on: 2018/8/5 20:53
 * description:
 */
@ActivityScoped
public class MainBlogPostsFragment extends BaseFragment<LayoutRefreshBinding, MainBlogPostsViewModel> {

    public static final String TYPE = "type";

    public static final String ID = "id";
    /**
     * 首页
     */
    public static final int TYPE_MAIN = 11;

    /**
     *  分类详情列表
     */
    public static final int TYPE_CATEGORY = 12;

    private MainBlogPostsAdapter mAdapter;

    @Inject
    Optional<Banner> mBanner;

    private int mType;
    private int mId;

    private List<BannerBean> bannerBeanList;

    @Inject
    public MainBlogPostsFragment() {
    }

    @Override
    public void onArgumentsHandle(@NonNull Bundle bundle) {
        mType = bundle.getInt(TYPE, 0);
        mId = bundle.getInt(ID, 0);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(TYPE, mType);
        outState.putInt(ID, mId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mType = savedInstanceState.getInt(TYPE);
            mId = savedInstanceState.getInt(ID, 0);
        }
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onBinding(@NonNull LayoutRefreshBinding binding) {

    }

    @Override
    public void onObserveViewModel(@NonNull MainBlogPostsViewModel viewModel) {
        mAdapter = new MainBlogPostsAdapter(viewModel);
        RefreshLoadHelper.init(mAdapter, binding.list);
        if (mType == TYPE_MAIN) {
            mAdapter.addHeaderView(mBanner.get());
            mBanner.get().setOnBannerListener(position -> ReadActivity.launch(MainBlogPostsFragment.this, bannerBeanList.get(position).getTitle(), bannerBeanList.get(position).getUrl()));
        }

        viewModel.setType(mType);
        binding.setRefreshViewModel(viewModel);
        viewModel.mClickEvent.observe(this, blogPostBean -> ReadActivity.launch(this, blogPostBean));
    }

    @Override
    public void onLazyLoad() {
        binding.refresh.setRefreshing(true);
        if (mType == TYPE_MAIN) {
            viewModel.getObservableBanners().observeData(this, new DataCallbackImp<List<BannerBean>>() {
                @Override
                public void success(List<BannerBean> bean) {
                    bannerBeanList = bean;
                    mBanner.get().setImages(bean);
                    mBanner.get().start();
                }

            });

            viewModel.getObservableBlogPosts().observe(this, RefreshLoadHelper.listener(this, binding.list, mAdapter, binding.refresh, viewModel));
        } else if (mType == TYPE_CATEGORY) {
            viewModel.setId(mId);
            viewModel.getObservablePostTreeDetailList(mId).observe(this, RefreshLoadHelper.listener(this, binding.list, mAdapter, binding.refresh, viewModel));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.setId(mId);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mType == TYPE_MAIN) {
            mBanner.get().startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mType == TYPE_MAIN) {
            mBanner.get().stopAutoPlay();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResultHelper.handlePost(requestCode, resultCode, data, mAdapter);
    }

    @Override
    public boolean isInViewPager() {
        return mType == TYPE_CATEGORY;
    }
}
