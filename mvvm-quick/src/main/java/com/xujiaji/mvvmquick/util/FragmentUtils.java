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

package com.xujiaji.mvvmquick.util;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * author: xujiaji
 * created on: 2018/6/13 15:32
 * description: Fragment 工具类
 */
public class FragmentUtils
{

    /**
     * 创建Fragment的实例
     *
     * @param keys   设置参数时的key
     * @param values 对应key的值
     * @return 创建好的Fragment实例
     */
    public static <T extends Fragment> T setArgs(T fragment, String[] keys, String ... values)
    {

        if (keys != null && values != null && keys.length != 0)
        {
            if (keys.length != values.length)
                throw new RuntimeException("keys size must be equal values size");

            Bundle args = new Bundle();

            for (int i = 0; i < keys.length; i++)
            {
                args.putString(keys[i], values[i]);
            }

            fragment.setArguments(args);
        }

        return fragment;

    }

}
