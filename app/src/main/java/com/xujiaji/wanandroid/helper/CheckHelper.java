package com.xujiaji.wanandroid.helper;

import android.text.TextUtils;

/**
 * author: xujiaji
 * created on: 2018/8/10 10:09
 * description:
 */
public class CheckHelper {
    public static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > 5;
    }

}
