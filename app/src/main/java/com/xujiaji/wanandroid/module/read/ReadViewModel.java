package com.xujiaji.wanandroid.module.read;

import android.app.Application;
import android.support.annotation.NonNull;

import com.xujiaji.wanandroid.base.BaseViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/7 22:48
 * description:
 */
@Singleton
public class ReadViewModel extends BaseViewModel {

    @Inject
    public ReadViewModel(@NonNull Application application) {
        super(application);
    }
}
