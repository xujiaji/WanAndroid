package com.xujiaji.wanandroid.repository.remote;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.App;
import com.xujiaji.wanandroid.config.C;
import com.xujiaji.wanandroid.helper.PrefHelper;
import com.xujiaji.wanandroid.helper.ToastHelper;
import com.xujiaji.wanandroid.repository.bean.Result;

public class NetLiveEvent<T> extends MutableLiveData<MutableLiveData<Result<T>>> {


    //抽取观察网络请求数据逻辑
    /**
     * 观察数据
     */
    public void observeData(@NonNull final LifecycleOwner owner, @NonNull final DataCallback<T> callback) {
        observeData(owner, false, callback);
    }

    /**
     * 观察数据
     * @param dataNullable 数据结果是否可能为null
     */
    public void observeData(@NonNull final LifecycleOwner owner, boolean dataNullable, @NonNull final DataCallback<T> callback) {
        observe(owner, new Observer<MutableLiveData<Result<T>>>() {
            @Override
            public void onChanged(@Nullable MutableLiveData<Result<T>> resultMutableLiveData) {
                if (resultMutableLiveData == null) {
                    callback.finished();
                    callback.fail(C.ERROR_UNKNOWN, null);
                    return;
                }
                resultMutableLiveData.observe(owner, new Observer<Result<T>>() {
                    @Override
                    public void onChanged(@Nullable Result<T> tResult) {
                        callback.finished();
                        if (tResult == null) {
                            callback.fail(C.ERROR_UNKNOWN, null);
                            return;
                        }

                        if (tResult.getErrorCode() == Net.ZERO) {
                            if (tResult.getData() == null && !dataNullable) {
                                callback.fail(C.ERROR_NULL_DATA, null);
                            } else {
                                callback.success(tResult.getData());
                            }
                        } else {
                            if (tResult.getErrorCode() == Net.NOT_LOGIN) {
                                ToastHelper.error(App.getInstance().getString(R.string.not_login));
                                App.Login.out();
                            }
                            callback.fail(tResult.getErrorCode(), tResult.getErrorMsg());
                        }
                    }
                });
            }
        });

    }

}
