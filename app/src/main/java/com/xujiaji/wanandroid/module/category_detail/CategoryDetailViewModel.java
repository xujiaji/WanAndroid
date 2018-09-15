package com.xujiaji.wanandroid.module.category_detail;

import android.app.Application;
import android.support.annotation.NonNull;

import com.xujiaji.wanandroid.base.BaseViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/9/15 0:41
 * description:
 */
@Singleton
public class CategoryDetailViewModel extends BaseViewModel {

//    public final ObservableField<String> thumb = new ObservableField<>();

    @Inject
    public CategoryDetailViewModel(@NonNull Application application) {
        super(application);
    }
}
