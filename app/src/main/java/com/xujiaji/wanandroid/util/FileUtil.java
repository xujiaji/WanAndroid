package com.xujiaji.wanandroid.util;

import android.content.Context;
import android.os.Environment;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.DecimalFormat;

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

    /**
     *
     * ===================================================
     *
     * File Size
     *
     * ===================================================
     */

    public static String getCacheSizeStr(Context context) {
        long size = 0;
        size += getDirSize(context.getCacheDir());
        if (context.getExternalCacheDir() != null)
            size += getDirSize(context.getExternalCacheDir());
        return readableFileSize(size);
    }

    public static long getDirSize(File dir){
        long size = 0;
        for (File file : dir.listFiles()) {
            if (file != null && file.isDirectory()) {
                size += getDirSize(file);
            } else if (file != null && file.isFile()) {
                size += file.length();
            }
        }
        return size;
    }

    public static String readableFileSize(long size) {
        if (size <= 0) return "0 Bytes";
        final String[] units = new String[]{"Bytes", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }


    /**
     *
     * ===================================================
     *
     * File Delete
     *
     * ===================================================
     */

    public static boolean deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            File dirOut = context.getExternalCacheDir();

            boolean delDir = false;
            boolean delDirOut = false;

            if (dir != null && dir.isDirectory()) {
                delDir = deleteDir(dir);
            }

            if (dirOut != null && dirOut.isDirectory()) {
                delDirOut = deleteDir(dirOut);
            }

            return delDir || delDirOut;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteDir(@NotNull File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }
}
