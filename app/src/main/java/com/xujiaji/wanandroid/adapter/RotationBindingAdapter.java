package com.xujiaji.wanandroid.adapter;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * author: xujiaji
 * created on: 2018/8/19 14:03
 * description:
 */
public class RotationBindingAdapter {
    @BindingAdapter("bind:rotationAnim")
    public static void rotationAnim(View view, int rotation) {
        view.animate().setDuration(400).rotation(rotation).start();
    }
}
