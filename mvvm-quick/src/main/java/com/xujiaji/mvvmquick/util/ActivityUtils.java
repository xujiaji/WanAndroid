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

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Activity 工具类
 */
public class ActivityUtils
{

    public static void replaceFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment,
                                                 int frameId) {
        replaceFragmentInActivity(fragmentManager, fragment, frameId, null, null);
    }

    /**
     * 用replace向Activity中添加Fragment
     * @param fragmentManager FragmentManger
     * @param fragment Fragment
     * @param frameId 添加到View容器的id
     * @param tag fragment tag
     * @param toBackStackStr 添加到返回堆栈
     */
    public static void replaceFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment,
                                                 int frameId,
                                                 String tag,
                                                 String toBackStackStr) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (TextUtils.isEmpty(tag))
        {
            transaction.replace(frameId, fragment);
        } else
        {
            transaction.replace(frameId, fragment, tag);
        }

        if (!TextUtils.isEmpty(toBackStackStr))
        {
            transaction.addToBackStack(toBackStackStr);
        }
        transaction.commit();

    }

    /**
     * 用add将Fragment添加到Activity
     * @param fragmentManager FragmentManger
     * @param fragment Fragment
     * @param frameId 添加到View容器的id
     * @param tag fragment tag
     */
    public static void addFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment, int frameId, String tag) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag);
        transaction.commit();
    }


    private static long exitTime = 0;
    /**
     * 作用如：2s内双击两次返回则退出程序
     *
     * @return 是否退出程序
     */
    public static boolean exitBy2Click()
    {
        return exitBy2Click(2000);
    }

    /**
     * 在某个时间段内双击两次
     *
     * @param space 两次点击最大时间间隔
     * @return 是否退出
     */
    public static boolean exitBy2Click(int space)
    {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            exitTime = System.currentTimeMillis();
            return false;
        } else {
            return true;
        }
    }


//    /**
//     * 初始化Toolbar，添加返回按钮，set title
//     * @param toolbar
//     */
//    public static void initBar(Toolbar toolbar, @StringRes int title) {
//        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_24dp);
//        TextView titleView = (TextView) LayoutInflater.from(toolbar.getContext()).inflate(R.layout.text_view_new_title, null);
//        titleView.setText(title);
//        Toolbar.LayoutParams params = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER;
//        toolbar.addView(titleView, params);
//    }


    public static void initSatus(View view)
    {
        int statusHeight = ScreenUtils.getStatusHeight(view.getContext());
        ViewGroup.LayoutParams statusParams = view.getLayoutParams();
        statusParams.height = statusHeight;
        view.setLayoutParams(statusParams);
    }

//    private static long lastClickTime;
//
//    public static boolean isFastClick()
//    {
//        return isFastClick(false);
//    }
//
//    public static boolean isFastClick(boolean isPrompt)
//    {
//        long time = System.currentTimeMillis();
//        long timeD = time - lastClickTime;
//        if (timeD < 2000)
//        {
//            if (isPrompt)
//            {
//                ToastUtil.getInstance().s("重复点击！");
//            }
//            return true;
//        } else
//        {
//            lastClickTime = time;
//            return false;
//        }
//    }
}
