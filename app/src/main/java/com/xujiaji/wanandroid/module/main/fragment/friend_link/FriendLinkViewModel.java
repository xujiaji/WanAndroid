package com.xujiaji.wanandroid.module.main.fragment.friend_link;

import android.app.Application;
import android.support.annotation.NonNull;

import com.xujiaji.wanandroid.base.BaseRefreshViewModel;
import com.xujiaji.wanandroid.repository.bean.FriendLinkBean;
import com.xujiaji.wanandroid.repository.remote.NetLiveEvent;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/8/25 12:40
 * description:
 */
@Singleton
public class FriendLinkViewModel extends BaseRefreshViewModel<FriendLinkBean> {

    private final NetLiveEvent<List<FriendLinkBean>> mFriendLinksData = new NetLiveEvent<>();

    @Inject
    public FriendLinkViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onListRefresh() {
        mFriendLinksData.setValue(net.get().getFriendLinks());
    }

    public NetLiveEvent<List<FriendLinkBean>> getObservableFriendLinksData() {
        mFriendLinksData.setValue(net.get().getFriendLinks());
        return mFriendLinksData;
    }
}
