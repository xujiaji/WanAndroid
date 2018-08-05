package com.xujiaji.wanandroid.base;

import android.support.annotation.NonNull;

import com.xujiaji.mvvmquick.base.MQApp;
import com.xujiaji.wanandroid.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

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
}
