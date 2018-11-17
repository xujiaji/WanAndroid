package com.xujiaji.wanandroid.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.xujiaji.wanandroid.base.App;
import com.xujiaji.wanandroid.repository.remote.Net;

import java.util.Map;

/**
 * Created by kosh20111 on 19 Feb 2017, 2:01 AM
 */
public class PrefHelper {
    /**
     * 用户信息key
     */
    public static final String USER_INFO = "user_info";

    private static Context mHostContext;
    private static boolean isFailCreateHostContext;

    /**
     * 获取玩安卓客户端Context
     */
    private static Context getShareUserContext() {
        if (mHostContext == null) {
            if (isFailCreateHostContext) return null;
            synchronized (PrefHelper.class) {
                try {
                    mHostContext = App.getInstance().createPackageContext("com.xujiaji.todo",
                            Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    isFailCreateHostContext = true;
                }
            }
        }
        return mHostContext;
    }

    /**
     * @param key   ( the Key to used to retrieve this data later  )
     * @param value ( any kind of primitive values  )
     *              <p/>
     *              non can be null!!!
     */

    public static <T> void set(@NonNull String key, @Nullable T value) {
        if (InputHelper.isEmpty(key)) {
            throw new NullPointerException("Key must not be null! (key = " + key + "), (value = " + value + ")");
        }
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(App.getInstance()).edit();
        if (InputHelper.isEmpty(value)) {
            clearKey(key);
            return;
        }
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else {
            edit.putString(key, value.toString());
        }
        edit.apply();//apply on UI
    }

    @Nullable
    public static String getString(@NonNull String key) {
        String value = PreferenceManager.getDefaultSharedPreferences(App.getInstance()).getString(key, null);
        if (value == null) {
            if (getShareUserContext() == null) return null;
            return PreferenceManager.getDefaultSharedPreferences(getShareUserContext()).getString(key, null);
        } else {
            return value;
        }
    }

    public static boolean getBoolean(@NonNull String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        return preferences.getAll().get(key) instanceof Boolean && preferences.getBoolean(key, false);
    }

    public static int getInt(@NonNull String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        return preferences.getAll().get(key) instanceof Integer ? preferences.getInt(key, 0) : -1;
    }

    public static long getLong(@NonNull String key) {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance()).getLong(key, 0);
    }

    public static float getFloat(@NonNull String key) {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance()).getFloat(key, 0);
    }

    public static void clearKey(@NonNull String key) {
        if (Net.SAVE_USER_LOGIN_KEY.equals(key) || PrefHelper.USER_INFO.equals(key)) {
            String value = PreferenceManager.getDefaultSharedPreferences(App.getInstance()).getString(key, null);
            if (value == null) {
                if (getShareUserContext() == null) return;
                value = PreferenceManager.getDefaultSharedPreferences(getShareUserContext()).getString(key, null);
                if (value == null) return;
                PreferenceManager.getDefaultSharedPreferences(getShareUserContext()).edit().remove(key).apply();
            }
        }
        PreferenceManager.getDefaultSharedPreferences(App.getInstance()).edit().remove(key).apply();
    }

    public static boolean isExist(@NonNull String key) {
        boolean value = PreferenceManager.getDefaultSharedPreferences(App.getInstance()).contains(key);
        return value || getShareUserContext() != null && PreferenceManager.getDefaultSharedPreferences(getShareUserContext()).contains(key);
    }

    public static void clearPrefs() {
        PreferenceManager.getDefaultSharedPreferences(App.getInstance()).edit().clear().apply();
    }

    public static Map<String, ?> getAll() {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance()).getAll();
    }
}
