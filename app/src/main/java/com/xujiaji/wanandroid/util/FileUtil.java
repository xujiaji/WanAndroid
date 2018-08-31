package com.xujiaji.wanandroid.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * author: xujiaji
 * created on: 2018/8/31 14:39
 * description:
 */
public class FileUtil {

    public static File getDiskCacheDir(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            File file = context.getExternalCacheDir();
            if (file != null) {
                return file;
            } else {
                return context.getCacheDir();
            }
        } else {
            return context.getCacheDir();
        }
    }

}
