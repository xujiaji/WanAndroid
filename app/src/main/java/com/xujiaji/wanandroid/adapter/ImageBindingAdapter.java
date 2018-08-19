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

package com.xujiaji.wanandroid.adapter;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xujiaji.wanandroid.config.PicConfig;

/**
 * author: xujiaji
 * created on: 2018/6/13 16:21
 * description:
 */
public class ImageBindingAdapter {

    @BindingAdapter("bind:itemUrl")
    public static void setImage(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            imageView.setVisibility(View.GONE);
            return;
        } else {
            imageView.setVisibility(View.VISIBLE);
        }

        Glide.with(imageView)
                .applyDefaultRequestOptions(PicConfig.itemOptions)
                .load(url)
                .into(imageView);
    }
//
//    @BindingAdapter("app:url")
//    public static void setAvatarImage(AvatarLayout avatarLayout, String url) {
//
//    }
}

