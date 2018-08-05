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

package com.xujiaji.mvvmquick.base;

import android.content.Context;

import dagger.android.support.DaggerApplication;

/**
 * author: xujiaji
 * created on: 2018/7/2 14:18
 * description:
 */
public abstract class MQApp extends DaggerApplication
{

    private static Context mInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this.getApplicationContext();
//        AppInjector.init(this);
    }

    public static Context getInstance()
    {
        return mInstance;
    }

}
