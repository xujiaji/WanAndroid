package com.xujiaji.wanandroid.module.main.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.DrawerFragmentMenuBinding;
import com.xujiaji.wanandroid.helper.ToastHelper;
import com.xujiaji.wanandroid.module.main.MainActivity;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/4 9:50
 * description:
 */
@ActivityScoped
public class MenuDrawerFragment extends BaseFragment<DrawerFragmentMenuBinding, MQViewModel> implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    MainActivity mainActivity;

    @Inject
    public MenuDrawerFragment() {}

    @Override
    public void onBinding(@NonNull DrawerFragmentMenuBinding binding) {
        binding.mainNav.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mainActivity.changeBottomNavigation(item.getItemId());
        return true;
    }
}
