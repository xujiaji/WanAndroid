package com.xujiaji.wanandroid.module.main.fragment.posts;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.App;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.FragmentMainBlogPostsBinding;
import com.xujiaji.wanandroid.helper.GlideImageLoader;
import com.xujiaji.wanandroid.helper.RefreshLoadHelper;
import com.xujiaji.wanandroid.helper.ViewHelper;
import com.xujiaji.wanandroid.module.read.ReadActivity;
import com.xujiaji.wanandroid.repository.bean.BannerBean;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;
import com.xujiaji.wanandroid.repository.bean.Result;
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
        viewModel.getObservableBanners().observe(this, new Observer<MutableLiveData<Result<List<BannerBean>>>>() {
            @Override
            public void onChanged(@Nullable MutableLiveData<Result<List<BannerBean>>> resultMutableLiveData) {
                if (resultMutableLiveData == null) return;
                resultMutableLiveData.observe(getActivity(), new Observer<Result<List<BannerBean>>>() {
                    @Override
                    public void onChanged(@Nullable Result<List<BannerBean>> bannerBeanResult) {
                        if (bannerBeanResult == null) return;
                        mBanner.setImages(bannerBeanResult.getData());
                        mBanner.start();
                    }
                });
            }
        });
        viewModel.getObservableBlogPosts().observe(this, RefreshLoadHelper.listener(this, mAdapter, binding.refresh, viewModel));
        viewModel.mClickEvent.observe(this, new Observer<BlogPostBean>() {
            @Override
            public void onChanged(@Nullable BlogPostBean blogPostBean) {
                ReadActivity.launch(getActivity(), blogPostBean);
            }
        });
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
