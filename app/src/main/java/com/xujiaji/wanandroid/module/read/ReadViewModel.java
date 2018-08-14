package com.xujiaji.wanandroid.module.read;

import android.app.Application;
import android.support.annotation.NonNull;

import com.xujiaji.wanandroid.base.BaseViewModel;
import com.xujiaji.wanandroid.repository.remote.NetLiveEvent;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/7 22:48
 * description:
 */
@Singleton
public class ReadViewModel extends BaseViewModel {

    private final NetLiveEvent<String> mCollect = new NetLiveEvent<>();
    private final NetLiveEvent<String> mUncollect = new NetLiveEvent<>();

    @Inject
    public ReadViewModel(@NonNull Application application) {
        super(application);
    }

    public NetLiveEvent<String> postObservableCollect(int id) {
        mCollect.setValue(net.get().postCollect(id));
        return mCollect;
    }

    public NetLiveEvent<String> postObservableUncollect(int id) {
        mUncollect.setValue(net.get().postUncollect(id));
        return mUncollect;
    }
}
