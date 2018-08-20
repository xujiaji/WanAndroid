package com.xujiaji.wanandroid.helper;

import android.content.Context;

/**
 * author: xujiaji
 * created on: 2018/8/20 22:55
 * description:
 */
public class ClipHelper {
    public static boolean copyToClipboard(Context context, String text) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
        if (clipboard == null) return false;
        clipboard.setPrimaryClip(clip);
        return true;
    }
}

