package com.xujiaji.wanandroid.wedgit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.config.PicConfig;

/**
 * author: xujiaji
 * created on: 2018/8/4 11:28
 * description:
 */
public class AvatarLayout extends FrameLayout {
    private ImageView avatar;

    public AvatarLayout(@NonNull Context context) {
        super(context);
    }

    public AvatarLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AvatarLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = inflate(getContext(), R.layout.layout_avatar, this);
        avatar = view.findViewById(R.id.avatar);
    }

    public void setUrl(String url) {
        Glide.with(getContext())
                .applyDefaultRequestOptions(PicConfig.headOptions)
                .load(url)
                .into(avatar);
    }

}
