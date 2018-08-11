package com.xujiaji.wanandroid.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.config.PicConfig;
import com.xujiaji.wanandroid.repository.bean.BannerBean;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

public class GlideImageLoader implements ImageLoaderInterface<View> {

    @Override
    public void displayImage(Context context, Object bannerData, View view) {
        BannerBean bannerBean = (BannerBean) bannerData;
        //Glide 加载图片简单用法
        Glide.with(context)
                .applyDefaultRequestOptions(PicConfig.itemOptions)
                .load(bannerBean.getImagePath())
                .into((ImageView) view.findViewById(R.id.bannerImg));
    }

    @Override
    public View createImageView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.item_layout_home_banner, null);
    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//    @Override
//    public ImageView createImageView(Context context) {
//        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//        SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
//        return simpleDraweeView;
//    }
}