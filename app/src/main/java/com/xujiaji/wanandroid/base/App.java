package com.xujiaji.wanandroid.base;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.xujiaji.mvvmquick.base.MQApp;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.di.DaggerAppComponent;
import com.xujiaji.wanandroid.helper.PrefHelper;
import com.xujiaji.wanandroid.repository.remote.Net;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import es.dmoral.toasty.Toasty;

/**
 * author: xujiaji
 * created on: 2018/8/3 10:01
 * description:
 */
public class App extends MQApp {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Login.isOK = PrefHelper.isExist(Net.SAVE_USER_LOGIN_KEY);
        Toasty.Config.getInstance()
                .setInfoColor(ContextCompat.getColor(this, R.color.colorAccent))
                .apply();
    }

    @NonNull
    public static App getInstance() {
        return instance;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                .build();

    }

    /**
     * 登录状态
     */
    public static class Login {
        private static boolean isOK;
        /**
         *  当前是否登录
         */
        public static boolean isOK() {
            return isOK;
        }

        /**
         * 已登录
         */
        public static void in() {
            isOK = true;
        }

        /**
         * 登出
         */
        public static void out() {
            PrefHelper.clearKey(Net.SAVE_USER_LOGIN_KEY);
            isOK = false;
        }
    }

}
