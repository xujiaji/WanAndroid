package com.xujiaji.wanandroid.module.read;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;

import com.just.agentweb.AgentWeb;
import com.xujiaji.mvvmquick.util.LogUtil;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseActivity;
import com.xujiaji.wanandroid.databinding.ActivityReadBinding;
import com.xujiaji.wanandroid.helper.FabPopLayoutHelper;
import com.xujiaji.wanandroid.helper.ToastHelper;
import com.xujiaji.wanandroid.helper.ToolbarHelper;
import com.xujiaji.wanandroid.helper.ViewHelper;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;
import com.xujiaji.wanandroid.repository.remote.DataCallbackImp;
import com.xujiaji.wanandroid.util.NetUtil;

/**
 * author: xujiaji
 * created on: 2018/8/7 21:58
 * description: read blog post
 */
public class ReadActivity extends BaseActivity<ActivityReadBinding, ReadViewModel> {

    public static final int ACTIVITY_REQUEST_CODE = 222;

    private BlogPostBean mPostBean;

    private AgentWeb mAgentWeb;

    public static void launch(@Nullable Fragment fragment, String title, String link) {
        BlogPostBean blogPostBean = new BlogPostBean();
        blogPostBean.setTitle(title);
        blogPostBean.setLink(link);
        launch(fragment, blogPostBean);
    }

    public static void launch(@Nullable Fragment fragment, BlogPostBean postBean) {
        if (fragment == null) return;
        Intent intent = new Intent(fragment.getContext(), ReadActivity.class);
        intent.putExtra(BlogPostBean.class.getSimpleName(), postBean);
        fragment.startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ToolbarHelper.initTranslucent(this);
        if (savedInstanceState == null) {
            mPostBean = getIntent().getParcelableExtra(BlogPostBean.class.getSimpleName());
        }
        super.onCreate(savedInstanceState);
        LogUtil.e3("onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BlogPostBean.class.getSimpleName(), mPostBean);
        LogUtil.e3("onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPostBean = savedInstanceState.getParcelable(BlogPostBean.class.getSimpleName());
        LogUtil.e3("onRestoreInstanceState");
    }

    @Override
    public void onBinding(ActivityReadBinding binding) {
        super.onBinding(binding);
        binding.includeBar.toolbar.setTitle(mPostBean.getTitle());
        ToolbarHelper.initFullBar(binding.includeBar.toolbar, this);
        FabPopLayoutHelper.initPopLayout(binding.fab, binding.backDrop, binding.layoutBrowse, binding.layoutLike);

        CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(-1, -1);
        lp.setBehavior(new AppBarLayout.ScrollingViewBehavior());

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(binding.coordinator, 1, lp)
                .useDefaultIndicator(ContextCompat.getColor(ReadActivity.this, R.color.colorAccent), 2)
                .setWebView((WebView) LayoutInflater.from(this).inflate(R.layout.webview, binding.coordinator, false))
                .createAgentWeb()
                .ready()
                .go(mPostBean.getLink());

        if (isNotBlogPost()) {
            binding.cardLike.setVisibility(View.GONE);
            binding.fabLike.setVisibility(View.GONE);
        } else {
            initFab();
        }
    }

    private void initFab() {
        ViewHelper.tintDrawable(binding.fabLike.getDrawable(), mPostBean.isCollect() ? ContextCompat.getColor(this, android.R.color.holo_red_light) : ContextCompat.getColor(this, R.color.grey_400));
    }

    @Override
    public boolean onSupportNavigateUp() {
        setResultData();
        finish();
        return true;
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (binding.backDrop.getVisibility() == View.VISIBLE) {
            binding.backDrop.performClick();
            return;
        }

        if (!mAgentWeb.back()) {
            setResultData();
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    // 是否是博文
    private boolean isNotBlogPost() {
        return mPostBean.getId() == 0;
    }

    /**
     * 设置返回数据
     */
    private void setResultData() {
        if (mPostBean == null || isNotBlogPost()) return;
        Intent intent = new Intent();
        intent.putExtra(BlogPostBean.class.getSimpleName(), mPostBean);
        setResult(RESULT_OK, intent);
    }

    public void onClickSystemBrowseOpen(View view) {
        onBackPressed();
        NetUtil.systemBrowserOpen(this, mPostBean.getLink());
    }

    public void onClickLike(View view) {
        onBackPressed();
        if (mPostBean == null) return;
        if (mPostBean.isCollect()) {
            viewModel.postObservableUncollect(mPostBean.getId()).observeData(this, true, new DataCallbackImp<String>() {
                @Override
                public void success(String bean) {
                    mPostBean.setCollect(false);
                    initFab();
                    ToastHelper.successUncollect();
                }
            });
        } else {
            viewModel.postObservableCollect(mPostBean.getId()).observeData(this, true, new DataCallbackImp<String>() {
                @Override
                public void success(String bean) {
                    mPostBean.setCollect(true);
                    initFab();
                    ToastHelper.successCollect();
                }
            });
        }
    }

}
