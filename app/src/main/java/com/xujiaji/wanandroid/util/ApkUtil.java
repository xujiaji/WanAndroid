package com.xujiaji.wanandroid.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * author: xujiaji
 * created on: 2018/10/18 14:18
 * description:
 */
public class ApkUtil {
    public static boolean isInstalled(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
