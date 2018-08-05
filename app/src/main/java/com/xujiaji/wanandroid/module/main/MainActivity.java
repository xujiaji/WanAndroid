package com.xujiaji.wanandroid.module.main;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.xujiaji.mvvmquick.base.MQActivity;
import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.util.ScreenUtils;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.adapter.FragmentsPagerAdapter;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.ActivityMainBinding;
import com.xujiaji.wanandroid.helper.ViewHelper;
import com.xujiaji.wanandroid.model.FragmentModel;
import com.xujiaji.wanandroid.util.StatusBarUtil;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends MQActivity<ActivityMainBinding, MQViewModel> {

    @Inject
    FragmentsPagerAdapter mDrawerPagerAdapter;

    @Inject
    @Named("Blog")
    FragmentModel mBlogFragment;

    @Inject
    @Named("Project")
    FragmentModel mProjectFragment;

    @Inject
    @Named("Tool")
    FragmentModel mToolFragment;


    private ActionBar actionBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_blog_post:
                showFrag(mBlogFragment.getFragment());
                return true;
            case R.id.navigation_project:
                showFrag(mProjectFragment.getFragment());
                return true;
            case R.id.navigation_tool:
                showFrag(mToolFragment.getFragment());
                return true;
        }
        return false;
    };

    private void showFrag(BaseFragment frag) {
        getSupportFragmentManager()
                .beginTransaction()
                .hide(mBlogFragment.getFragment())
                .hide(mProjectFragment.getFragment())
                .hide(mToolFragment.getFragment())
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
                .add(R.id.container, mBlogFragment.getFragment(), "MainBlogPostsFragment")
                .add(R.id.container, mProjectFragment.getFragment(), "MainProjectsFragment")
                .add(R.id.container, mToolFragment.getFragment(), "MainToolsFragment")
                .hide(mProjectFragment.getFragment())
                .hide(mToolFragment.getFragment())
                .commit();
    }

    @Override
    public void onBinding(ActivityMainBinding binding) {
        super.onBinding(binding);
        binding.includeBar.statusBarHeightView.setLayoutParams(new AppBarLayout.LayoutParams(AppBarLayout.LayoutParams.MATCH_PARENT, ScreenUtils.getStatusHeight(this)));
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
