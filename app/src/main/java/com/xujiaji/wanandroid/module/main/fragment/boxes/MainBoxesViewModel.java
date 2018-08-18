package com.xujiaji.wanandroid.module.main.fragment.boxes;

import android.app.Application;
import android.support.annotation.NonNull;

import com.xujiaji.mvvmquick.lifecycle.SingleLiveEvent;
import com.xujiaji.wanandroid.base.BaseRefreshViewModel;
import com.xujiaji.wanandroid.repository.bean.BoxBean;
import com.xujiaji.wanandroid.repository.remote.NetLiveEvent;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/15 10:52
 * description:
 */
@Singleton
public class MainBoxesViewModel extends BaseRefreshViewModel<BoxBean> {

    public final SingleLiveEvent<BoxBean> mClickEvent = new SingleLiveEvent<>();
    public final SingleLiveEvent<BoxBean> mClickGithubEvent = new SingleLiveEvent<>();

    private final NetLiveEvent<List<BoxBean>> mBoxes = new NetLiveEvent<>();

    @Inject
    public MainBoxesViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onListRefresh() {
        super.onListRefresh();
        mBoxes.setValue(net.get().getBoxes());
    }

    public NetLiveEvent<List<BoxBean>> getBoxes() {
        mBoxes.setValue(net.get().getBoxes());
        return mBoxes;
    }
}
