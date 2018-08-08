package com.xujiaji.wanandroid.module.read;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.NestedScrollAgentWebView;
import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.util.LogUtil;
import com.xujiaji.mvvmquick.util.ToastUtil;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseActivity;
import com.xujiaji.wanandroid.databinding.ActivityReadBinding;
import com.xujiaji.wanandroid.helper.AnimHelper;
import com.xujiaji.wanandroid.helper.FabPopLayoutHelper;
import com.xujiaji.wanandroid.helper.ToolbarHelper;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;

import java.util.Arrays;

/**
 * author: xujiaji
 * created on: 2018/8/7 21:58
 * description: read blog post
 */
public class ReadActivity extends BaseActivity<ActivityReadBinding, MQViewModel> {

    private BlogPostBean mPostBean;

    private AgentWeb mAgentWeb;

    public static void launch(@Nullable Context context, BlogPostBean postBean) {
        if (context == null) return;
        Intent intent = new Intent(context, ReadActivity.class);
        intent.putExtra(BlogPostBean.class.getSimpleName(), postBean);
        context.startActivity(intent);
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
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
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
        if (!mAgentWeb.back()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
