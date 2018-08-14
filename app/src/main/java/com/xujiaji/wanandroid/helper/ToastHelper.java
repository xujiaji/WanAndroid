package com.xujiaji.wanandroid.helper;

import android.widget.Toast;

import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.App;

import es.dmoral.toasty.Toasty;

/**
 * author: xujiaji
 * created on: 2018/8/9 15:01
 * description:
 */
public class ToastHelper {

    public static void info(String msg) {
        Toasty.info(App.getInstance(), msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void success(String msg) {
        Toasty.success(App.getInstance(), msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void error(String msg) {
        Toasty.error(App.getInstance(), msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void warning(String msg) {
        Toasty.warning(App.getInstance(), msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void successCollect() {
        success(App.getInstance().getString(R.string.success_collect));
    }

    public static void successUncollect() {
        success(App.getInstance().getString(R.string.success_uncollect));
    }
}
