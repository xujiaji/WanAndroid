package com.xujiaji.wanandroid.config;

import com.bumptech.glide.request.RequestOptions;
import com.xujiaji.wanandroid.R;

/**
 * author: xujiaji
 * created on: 2018/8/7 9:35
 * description:
 */
public class PicConfig {
    public static RequestOptions itemOptions = new RequestOptions()
            .placeholder(R.mipmap.placeholder_item_pic)
            .error(R.mipmap.error_item_pic);


    public static RequestOptions headOptions = new RequestOptions()
            .placeholder(R.mipmap.ic_default_head)
            .error(R.mipmap.ic_default_head);

}
