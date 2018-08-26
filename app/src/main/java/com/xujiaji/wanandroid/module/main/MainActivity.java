package com.xujiaji.wanandroid.module.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.util.ActivityUtils;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.adapter.FragmentsPagerAdapter;
import com.xujiaji.wanandroid.base.App;
import com.xujiaji.wanandroid.base.BaseActivity;
import com.xujiaji.wanandroid.databinding.ActivityMainBinding;
import com.xujiaji.wanandroid.helper.BottomNavigationHelper;
import com.xujiaji.wanandroid.helper.PrefHelper;
import com.xujiaji.wanandroid.helper.ToastHelper;
import com.xujiaji.wanandroid.helper.ToolbarHelper;
import com.xujiaji.wanandroid.model.FragmentModel;
import com.xujiaji.wanandroid.module.login.LoginActivity;
import com.xujiaji.wanandroid.module.set.SettingsActivity;
import com.xujiaji.wanandroid.repository.bean.UserBean;

import java.util.List;

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
    @Named("Box")
    FragmentModel mToolModel;

    @Inject
    @Named("OPENAPIS")
    FragmentModel mOpenAPIModel;

    @Inject
    @Named("PostTree")
    FragmentModel mPostTreeModel;

    @Inject
    @Named("WebNav")
    FragmentModel mWebNavModel;

    @Inject
    @Named("ProjectCategory")
    FragmentModel mProjectCategoryModel;

    @Inject
    @Named("FriendLink")
    FragmentModel mFriendLinkModel;

    @Inject
    List<FragmentModel> mHomeFragModels;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_blog_post:
                showFrag(mBlogModel);
                return true;
            case R.id.navigation_project:
                showFrag(mProjectModel);
                return true;
            case R.id.navigation_box:
                showFrag(mToolModel);
                return true;
        }
        return false;
    };

    private void showFrag(FragmentModel fragModel) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (FragmentModel fm : mHomeFragModels) {
            if (manager.findFragmentByTag(fm.getFragment().getClass().getSimpleName()) != null)
                ft.hide(fm.getFragment());
        }
        if (manager.findFragmentByTag(fragModel.getFragment().getClass().getSimpleName()) != null) {
            ft.show(fragModel.getFragment()).commit();
        } else {
            ft.add(R.id.container, fragModel.getFragment(), fragModel.getFragment().getClass().getSimpleName()).commit();
        }

        binding.includeBar.toolbar.setTitle(fragModel.getTitle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ToolbarHelper.initTranslucent(this);
        super.onCreate(savedInstanceState);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, mBlogModel.getFragment(), mBlogModel.getFragment().getClass().getSimpleName())
                .add(R.id.container, mProjectModel.getFragment(), mProjectModel.getFragment().getClass().getSimpleName())
                .add(R.id.container, mToolModel.getFragment(), mToolModel.getFragment().getClass().getSimpleName())
                .hide(mProjectModel.getFragment())
                .hide(mToolModel.getFragment())
                .commit();

        changeAccount(new Gson().fromJson(PrefHelper.getString(PrefHelper.USER_INFO), UserBean.class));
        App.Login.event().observe(this, this::changeAccount);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBinding(ActivityMainBinding binding) {
        super.onBinding(binding);
        ToolbarHelper.initPaddingTopDiffBar(binding.navMenu.extrasNav);
        ToolbarHelper.initFullBar(binding.includeBar.toolbar, this);
        initDrawer(binding.drawer, binding.includeBar.toolbar);
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        binding.navMenu.drawerViewPager.setAdapter(mDrawerPagerAdapter);
        binding.navMenu.drawerTabLayout.setupWithViewPager(binding.navMenu.drawerViewPager);
        binding.fab.setOnClickListener(v -> {
            if (App.Login.isOK()) {
                ToastHelper.info("敬请期待！");
            } else {
                LoginActivity.launch(MainActivity.this);
            }
        });
    }

    private void changeAccount(UserBean userBean) {
        TextView tvName = binding.navMenu.extrasNav.getHeaderView(0).findViewById(R.id.navFullName);
        TextView tvEmail = binding.navMenu.extrasNav.getHeaderView(0).findViewById(R.id.navUsername);
        if (userBean != null) { // login
            tvName.setText(userBean.getEmail());
            tvEmail.setText(userBean.getUsername());
        } else { // no login
            tvName.setText(R.string.not_login);
            tvEmail.setText(R.string.app_name);
        }

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

    public void changeBottomNavigation(@IdRes int id) {
        onBackPressed();
        switch (id) {
            case R.id.navigation_set:
                SettingsActivity.launch(this);
                return;
            case R.id.navigation_about:
                ToastHelper.info("关于");
                return;
            case R.id.navigation_home:
                if (binding.navigation.getMenu().size() != 3) { //当前不为首页时，注意首页底部导航数量不为3时，必须修改这个值
                    binding.fab.setVisibility(View.VISIBLE);
                    binding.includeBar.toolbar.getMenu().findItem(R.id.search).setVisible(true);
                    showFrag(mBlogModel);
                    BottomNavigationHelper.showHome(binding.navigation);
                }
                return;
        }

        BottomNavigationHelper.onlyShow(binding.navigation, id);
        binding.fab.setVisibility(View.GONE);
        binding.includeBar.toolbar.getMenu().findItem(R.id.search).setVisible(false);
        switch (id) {
            case R.id.navigation_open_apis:
                showFrag(mOpenAPIModel);
                break;
            case R.id.navigation_knowledge_system:
                showFrag(mPostTreeModel);
                break;
            case R.id.navigation_navigation:
                showFrag(mWebNavModel);
                break;
            case R.id.navigation_project_category:
                showFrag(mProjectCategoryModel);
                break;
            case R.id.navigation_friend_links:
                showFrag(mFriendLinkModel);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (binding.drawer.isDrawerOpen(Gravity.START)) {
            binding.drawer.closeDrawer(Gravity.START);
            return;
        }
        if (ActivityUtils.exitBy2Click()) {
            super.onBackPressed();
        } else {
            ToastHelper.info(getString(R.string.again_touch_exit));
        }

    }
}
