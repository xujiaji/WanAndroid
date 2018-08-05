/*
 *    Copyright 2018 XuJiaji
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.xujiaji.mvvmquick.callback;

import android.arch.lifecycle.MutableLiveData;

import com.xujiaji.mvvmquick.util.ToastUtil;

import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: xujiaji
 * created on: 2018/6/12 16:42
 * description: 网络请求回调统一处理
 */
public class NetCallback<T> implements Callback<T>
{
    private final MutableLiveData<T> mutableLiveData;
    public NetCallback(MutableLiveData<T> mutableLiveData)
    {
        this.mutableLiveData = mutableLiveData;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response)
    {
        mutableLiveData.setValue(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t)
    {
        if (t instanceof UnknownHostException)
        {
            ToastUtil.getInstance().showShort("请检查网络");
        } else if (t instanceof TimeoutException)
        {
            ToastUtil.getInstance().showShort("连接超时");
        } else
        {
            ToastUtil.getInstance().showShort(t.getMessage());
        }
        mutableLiveData.setValue(null);
    }
}
