package com.xujiaji.wanandroid.module.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.adapter.FragmentsPagerAdapter;
import com.xujiaji.wanandroid.base.BaseActivity;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.ActivityMainBinding;
import com.xujiaji.wanandroid.helper.ToolbarHelper;
import com.xujiaji.wanandroid.model.FragmentModel;

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
        ToolbarHelper.initTranslucent(this);
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
        ToolbarHelper.initFullBar(binding.includeBar.toolbar, this);
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

}
