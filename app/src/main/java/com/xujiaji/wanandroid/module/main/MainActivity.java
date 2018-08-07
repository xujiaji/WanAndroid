package com.xujiaji.wanandroid.module.main;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.util.LogUtil;
import com.xujiaji.mvvmquick.util.ScreenUtils;
import com.xujiaji.mvvmquick.util.ToastUtil;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.adapter.FragmentsPagerAdapter;
import com.xujiaji.wanandroid.base.BaseActivity;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.ActivityMainBinding;
import com.xujiaji.wanandroid.helper.ViewHelper;
import com.xujiaji.wanandroid.model.FragmentModel;
import com.xujiaji.wanandroid.util.StatusBarUtil;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends BaseActivity<ActivityMainBinding, MQViewModel> {

    @Inject
    FragmentsPagerAdapter mDrawerPagerAdapter;

    @Inject
    @Named("Post")
    FragmentModel mBlogModel;

    @Inject
    @Named("Project")
    FragmentModel mProjectModel;

    @Inject
    @Named("Tool")
    FragmentModel mToolModel;

    private ActionBar actionBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_blog_post:
                showFrag(mBlogModel.getFragment());
                return true;
            case R.id.navigation_project:
                showFrag(mProjectModel.getFragment());
                return true;
            case R.id.navigation_tool:
                showFrag(mToolModel.getFragment());
                return true;
        }
        return false;
    };

    private void showFrag(BaseFragment frag) {
        getSupportFragmentManager()
                .beginTransaction()
                .hide(mBlogModel.getFragment())
                .hide(mProjectModel.getFragment())
                .hide(mToolModel.getFragment())
                .show(frag)
                .commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setLightMode(this);
        super.onCreate(savedInstanceState);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, mBlogModel.getFragment(), "MainBlogPostsFragment")
                .add(R.id.container, mProjectModel.getFragment(), "MainProjectsFragment")
                .add(R.id.container, mToolModel.getFragment(), "MainToolsFragment")
                .hide(mProjectModel.getFragment())
                .hide(mToolModel.getFragment())
                .commit();
    }

    @Override
    public void onBinding(ActivityMainBinding binding) {
        super.onBinding(binding);
        ViewGroup.LayoutParams params = binding.includeBar.toolbar.getLayoutParams();
        params.height = ScreenUtils.getStatusHeight(this) + ViewHelper.getSystemActionBarSize(this);
        binding.includeBar.toolbar.setLayoutParams(params);
        binding.includeBar.toolbar.setPadding(
                binding.includeBar.toolbar.getLeft(),
                binding.includeBar.toolbar.getTop() + ScreenUtils.getStatusHeight(this),
                binding.includeBar.toolbar.getRight(),
                binding.includeBar.toolbar.getBottom()
        );
        initToolbar(binding.includeBar.toolbar);
        initDrawer(binding.drawer, binding.includeBar.toolbar);
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        binding.navMenu.drawerViewPager.setAdapter(mDrawerPagerAdapter);
        binding.navMenu.drawerTabLayout.setupWithViewPager(binding.navMenu.drawerViewPager);
    }

    private void initDrawer(DrawerLayout drawer, Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
//                updateCounter(binding.navView);
                super.onDrawerOpened(drawerView);
            }
        };

        toggle.syncState();
    }


    private void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

}
