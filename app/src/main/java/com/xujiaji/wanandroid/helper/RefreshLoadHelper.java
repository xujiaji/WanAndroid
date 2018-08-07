package com.xujiaji.wanandroid.helper;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.xujiaji.mvvmquick.base.MQQuickAdapter;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.RefreshLoadViewModel;
import com.xujiaji.wanandroid.model.RefreshLoadModel;
import com.xujiaji.wanandroid.repository.bean.PageBean;
import com.xujiaji.wanandroid.repository.bean.Result;

/**
 * author: xujiaji
 * created on: 2018/8/7 13:59
 * description:
 */
public class RefreshLoadHelper {
    public static void init(SwipeRefreshLayout refresh, MQQuickAdapter adapter, RecyclerView list) {
        refresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorPrimary);
        adapter.bindToRecyclerView(list, true);
        adapter.setEmptyView(R.layout.no_item_archived, list);
    }


    public static  <T> Observer<RefreshLoadModel<MutableLiveData<Result<PageBean<T>>>>> listener(LifecycleOwner owner, MQQuickAdapter adapter, SwipeRefreshLayout refresh, RefreshLoadViewModel<T> viewModel) {
        return new Observer<RefreshLoadModel<MutableLiveData<Result<PageBean<T>>>>>() {
            @Override
            public void onChanged(@Nullable RefreshLoadModel<MutableLiveData<Result<PageBean<T>>>> mutableLiveDataRefreshLoadModel) {

                if (mutableLiveDataRefreshLoadModel == null) return;
                if (mutableLiveDataRefreshLoadModel.isRefresh) {
                    adapter.setEnableLoadMore(false);
                } else {
                    refresh.setEnabled(false);
                }

                mutableLiveDataRefreshLoadModel.data.observe(owner, new Observer<Result<PageBean<T>>>() {
                    @Override
                    public void onChanged(@Nullable Result<PageBean<T>> pageBeanResult) {
                        refresh.setEnabled(true);
                        adapter.setEnableLoadMore(true);
                        refresh.setRefreshing(false);
                        adapter.loadMoreComplete();
                        if (pageBeanResult == null) return;
                        if (mutableLiveDataRefreshLoadModel.isRefresh) {
                            viewModel.getList().clear();
                        } else {
                            if (pageBeanResult.getData().isOver()) {
                                adapter.setLoaded();
                            }
                        }
                        viewModel.getList().addAll(pageBeanResult.getData().getDatas());
                    }
                });
            }
        };
    }
}
