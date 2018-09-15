package com.xujiaji.wanandroid.module.like;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.xujiaji.mvvmquick.util.LogUtil;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseActivity;
import com.xujiaji.wanandroid.databinding.ActivityLikeBinding;
import com.xujiaji.wanandroid.helper.ActivityResultHelper;
import com.xujiaji.wanandroid.helper.RefreshLoadHelper;
import com.xujiaji.wanandroid.helper.ToastHelper;
import com.xujiaji.wanandroid.helper.ToolbarHelper;
import com.xujiaji.wanandroid.module.read.ReadActivity;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/28 19:37
 * description:
 */
public class LikeActivity extends BaseActivity<ActivityLikeBinding, LikeViewModel> {

    @Inject
    LikeAdapter mAdapter;

    public static void launch(@Nullable Context context) {
        if (context == null) return;
        context.startActivity(new Intent(context, LikeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ToolbarHelper.initTranslucent(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBinding(@NonNull ActivityLikeBinding binding) {
        binding.includeBar.toolbar.setTitle(R.string.like);
        ToolbarHelper.initFullBar(binding.includeBar.toolbar, this);
        RefreshLoadHelper.init(mAdapter, binding.layoutRefresh.list);

        binding.fab.setOnClickListener(v -> ToastHelper.info("敬请期待！"));
    }

    @Override
    public void onObserveViewModel(@NonNull LikeViewModel viewModel) {
        super.onObserveViewModel(viewModel);
        binding.layoutRefresh.setRefreshViewModel(viewModel);

        viewModel.getObservableCollects().observe(this, RefreshLoadHelper.listener(this, binding.layoutRefresh.list, mAdapter, binding.layoutRefresh.refresh, viewModel));
        viewModel.mClickEvent.observe(this, blogPostBean -> {
            blogPostBean.setCollect(true);
            ReadActivity.launch(this, blogPostBean);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReadActivity.ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            BlogPostBean postBean = data.getParcelableExtra(BlogPostBean.class.getSimpleName());
            if (postBean == null || mAdapter.getData().size() < postBean.getIndex()) return;
            if (!postBean.isCollect()) {
                mAdapter.remove(postBean.getIndex());
            }
        }
    }

}
