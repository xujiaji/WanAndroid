package com.xujiaji.wanandroid.module.main.fragment.projects;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;

import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.LayoutRefreshBinding;
import com.xujiaji.wanandroid.helper.ActivityResultHelper;
import com.xujiaji.wanandroid.helper.RefreshLoadHelper;
import com.xujiaji.wanandroid.module.read.ReadActivity;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/5 21:06
 * description:
 */
@ActivityScoped
public class MainProjectsFragment extends BaseFragment<LayoutRefreshBinding, MainProjectsViewModel> {
    @Inject
    MainProjectsAdapter mAdapter;

    @Inject
    public MainProjectsFragment() {}

    @Override
    public void onBinding(@NonNull LayoutRefreshBinding binding) {
        RefreshLoadHelper.init(mAdapter, binding.list);
    }

    @Override
    public void onObserveViewModel(@NonNull MainProjectsViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.setRefreshViewModel(viewModel);
        viewModel.getObservableProjects().observe(this, RefreshLoadHelper.listener(this, binding.list, mAdapter, binding.refresh, viewModel));
        viewModel.mClickEvent.observe(this, blogPostBean -> ReadActivity.launch(this, blogPostBean));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResultHelper.handlePost(requestCode, resultCode, data, mAdapter);
    }
}
