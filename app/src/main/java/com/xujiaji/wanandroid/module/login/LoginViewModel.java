package com.xujiaji.wanandroid.module.login;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.xujiaji.mvvmquick.lifecycle.SingleLiveEvent;
import com.xujiaji.wanandroid.base.BaseViewModel;
import com.xujiaji.wanandroid.repository.bean.Result;
import com.xujiaji.wanandroid.repository.bean.UserBean;
import com.xujiaji.wanandroid.repository.remote.NetLiveEvent;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/11 19:22
 * description:
 */
@Singleton
public class LoginViewModel extends BaseViewModel {

    private final NetLiveEvent<UserBean> mRegisterUserBean = new NetLiveEvent<>();
    private final NetLiveEvent<UserBean> mLoginUserBean = new NetLiveEvent<>();

    @Inject
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public NetLiveEvent<UserBean> postObservableLogin(String username, String password) {
        mLoginUserBean.setValue(net.get().postLogin(username, password));
        return mLoginUserBean;
    }

    public NetLiveEvent<UserBean> postObservableRegister(String username, String password) {
        mRegisterUserBean.setValue(net.get().postRegister(username, password));
        return mRegisterUserBean;
    }
}
