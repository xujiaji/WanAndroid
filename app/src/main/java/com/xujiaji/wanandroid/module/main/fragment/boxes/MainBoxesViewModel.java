package com.xujiaji.wanandroid.module.main.fragment.boxes;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.xujiaji.mvvmquick.lifecycle.SingleLiveEvent;
import com.xujiaji.wanandroid.base.BaseViewModel;
import com.xujiaji.wanandroid.repository.bean.ToolBean;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/15 10:52
 * description:
 */
@Singleton
public class MainBoxesViewModel extends BaseViewModel {

    public final SingleLiveEvent<ToolBean> mClickEvent = new SingleLiveEvent<>();
    public final ObservableList<ToolBean> items = new ObservableArrayList<>();

    @Inject
    public MainBoxesViewModel(@NonNull Application application) {
        super(application);
    }
}
