package com.xujiaji.wanandroid.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.google.gson.Gson;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.RePluginConfig;
import com.qihoo360.replugin.sdk.HostCallbacks;
import com.qihoo360.replugin.sdk.HostEventCallbacks;
import com.qihoo360.replugin.sdk.PluginConfig;
import com.qihoo360.replugin.sdk.PluginManager;
import com.squareup.leakcanary.LeakCanary;
import com.xujiaji.mvvmquick.base.MQApp;
import com.xujiaji.mvvmquick.lifecycle.SingleLiveEvent;
import com.xujiaji.wanandroid.BuildConfig;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.di.DaggerAppComponent;
import com.xujiaji.wanandroid.helper.PrefHelper;
import com.xujiaji.wanandroid.repository.bean.UserBean;
import com.xujiaji.wanandroid.repository.remote.Net;

import java.util.ArrayList;
import java.util.List;

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
        initRePlugin();
        initLeakCanary();
        Login.isOK = PrefHelper.isExist(Net.SAVE_USER_LOGIN_KEY);
        Toasty.Config.getInstance()
                .setInfoColor(ContextCompat.getColor(this, R.color.colorAccent))
                .apply();
    }

    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    private void initRePlugin() {
        RePlugin.App.onCreate(); // config RePlugin
        //1、设置AppKey
        PluginConfig.setAppKey("xyzjxo72ebui");
        PluginManager.init(getApplicationContext());
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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        RePluginConfig config = new RePluginConfig();

        config.setUseHostClassIfNotFound(true);
//        config.setVerifySign(!BuildConfig.DEBUG);
        config.setEventCallbacks(new HostEventCallbacks(this));
        config.setCallbacks(new HostCallbacks(this));

        RePlugin.App.attachBaseContext(this, config);
        RePlugin.enableDebugger(base, BuildConfig.DEBUG);
    }


    /**
     * 登录状态
     */
    public static class Login {

        private static final List<SingleLiveEvent<UserBean>> liveEventList = new ArrayList<>();

        private static boolean isOK;

        /**
         * 订阅登录成功事件
         */
        public static SingleLiveEvent<UserBean> event() {
            SingleLiveEvent<UserBean> le = new SingleLiveEvent<>();
            liveEventList.add(le);
            return le;
        }

        /**
         *  当前是否登录
         */
        public static boolean isOK() {
            return isOK;
        }

        /**
         * 已登录
         */
        public static void in(UserBean userBean) {
            isOK = true;
            PrefHelper.set(PrefHelper.USER_INFO, new Gson().toJson(userBean));

            for (SingleLiveEvent<UserBean> le : liveEventList) {
                le.setValue(userBean);
            }
        }

        /**
         * 登出
         */
        public static void out() {
            PrefHelper.clearKey(Net.SAVE_USER_LOGIN_KEY);
            PrefHelper.clearKey(PrefHelper.USER_INFO);
            for (SingleLiveEvent<UserBean> le : liveEventList) {
                le.setValue(null);
            }
            liveEventList.clear();
            isOK = false;
        }
    }

}
