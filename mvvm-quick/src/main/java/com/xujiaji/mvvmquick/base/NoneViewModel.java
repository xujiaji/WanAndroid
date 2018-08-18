package com.xujiaji.mvvmquick.base;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * author: xujiaji
 * created on: 2018/8/18 21:36
 * description: 没有ViewModel的情况
 */
public class NoneViewModel extends MQViewModel {

    public NoneViewModel(@NonNull Application application) {
        super(application);
    }
}
