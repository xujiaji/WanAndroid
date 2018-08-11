package com.xujiaji.wanandroid.repository.remote;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xujiaji.wanandroid.config.C;
import com.xujiaji.wanandroid.repository.bean.Result;

import java.util.concurrent.atomic.AtomicBoolean;

public class NetLiveEvent<T> extends MutableLiveData<MutableLiveData<Result<T>>> {

    private static final String TAG = "NetLiveEvent";

    private final AtomicBoolean mPending = new AtomicBoolean(false);

    public void observeData(@NonNull final LifecycleOwner owner, @NonNull final DataCallback<T> callback) {
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

                        if (tResult.getErrorCode() == Net.OK) {
                            if (tResult.getData() == null) {
                                callback.fail(C.ERROR_NULL_DATA, null);
                            } else {
                                callback.success(tResult.getData());
                            }
                        } else {
                            callback.fail(tResult.getErrorCode(), tResult.getErrorMsg());
                        }
                    }
                });
            }
        });
    }

}
