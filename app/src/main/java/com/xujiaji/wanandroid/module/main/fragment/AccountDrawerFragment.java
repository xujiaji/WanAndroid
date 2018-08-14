package com.xujiaji.wanandroid.module.main.fragment;

import android.view.View;

import com.annimon.stream.Stream;
import com.google.gson.Gson;
import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.App;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.databinding.DrawerFragmentAccountBinding;
import com.xujiaji.wanandroid.helper.PrefHelper;
import com.xujiaji.wanandroid.helper.ToastHelper;
import com.xujiaji.wanandroid.module.login.LoginActivity;
import com.xujiaji.wanandroid.module.main.MainActivity;
import com.xujiaji.wanandroid.repository.bean.UserBean;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/4 9:54
 * description:
 */
@ActivityScoped
public class AccountDrawerFragment extends BaseFragment<DrawerFragmentAccountBinding, MQViewModel> implements View.OnClickListener {

    @Inject
    MainActivity mainActivity;

    @Inject
    public AccountDrawerFragment() {
    }

    @Override
    public void onBinding(DrawerFragmentAccountBinding binding) {
        super.onBinding(binding);

        Stream.of(binding.collect, binding.heart, binding.share, binding.logout, binding.login)
                .forEach(frameLayout -> frameLayout.setOnClickListener(AccountDrawerFragment.this));

        changeAccount(new Gson().fromJson(PrefHelper.getString(PrefHelper.USER_INFO), UserBean.class));
        App.Login.event().observe(this, this::changeAccount);
    }

    @Override
    public void onClick(View v) {
        mainActivity.onBackPressed();
        switch (v.getId()) {
            case R.id.logout:
                App.Login.out();
                ToastHelper.info("已登出！");
                break;
            case R.id.login:
                LoginActivity.launch(getActivity());
                break;
            default:
                ToastHelper.info("敬请期待！");
                break;
        }
    }

    private void changeAccount(UserBean userBean) {
        if (userBean != null) { // login
            Stream.of(binding.collect, binding.heart, binding.share, binding.logout)
                    .forEach(frameLayout -> frameLayout.setVisibility(View.VISIBLE));
            binding.login.setVisibility(View.GONE);
        } else { // logout
            Stream.of(binding.collect, binding.heart, binding.share, binding.logout)
                    .forEach(frameLayout -> frameLayout.setVisibility(View.GONE));

            binding.login.setVisibility(View.VISIBLE);
        }
    }
}
