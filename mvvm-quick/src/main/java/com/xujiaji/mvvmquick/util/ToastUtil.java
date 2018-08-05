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

package com.xujiaji.mvvmquick.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorRes;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xujiaji.mvvmquick.R;
import com.xujiaji.mvvmquick.base.MQApp;


/**
 * Toast工具类
 */
public final class ToastUtil
{
    private static volatile ToastUtil mToastUtil = null;
    private Toast mToast = null;
    private TextView text = null;
    private CardView bg = null;
    protected Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 获取实例
     */
    public static ToastUtil getInstance()
    {
        if (mToastUtil == null)
        {
            synchronized (ToastUtil.class)
            {
                mToastUtil = new ToastUtil();
            }
        }
        return mToastUtil;
    }

    /**
     * 显示Toast，多次调用此函数时，Toast显示的时间不会累计，并且显示内容为最后一次调用时传入的内容
     * 持续时间默认为short
     *
     * @param tips 要显示的内容
     *             {@link Toast#LENGTH_LONG}
     */
    public void showShortNormal(final String tips)
    {
        showToast(tips, -1, Toast.LENGTH_SHORT, false, 0);
    }

    public void showShortNormal(final int tips)
    {
        showToast(null, tips, Toast.LENGTH_SHORT, false, 0);
    }

    public void showLongNormal(final String tips)
    {
        showToast(tips, -1, Toast.LENGTH_LONG, false, 0);
    }

    public void showLongNormal(final int tips)
    {
        showToast(null, tips, Toast.LENGTH_LONG, false, 0);
    }

    // -------------------------
    public void showShort(final String tips)
    {
        showToast(tips, -1, Toast.LENGTH_SHORT, true, R.color.overlay_light_90);
    }

    public void showShort(final int tips)
    {
        showToast(null, tips, Toast.LENGTH_SHORT, true, R.color.overlay_light_90);
    }

    public void showLong(final String tips)
    {
        showToast(tips, -1, Toast.LENGTH_LONG, true, R.color.overlay_light_90);
    }

    public void showLong(final int tips)
    {
        showToast(null, tips, Toast.LENGTH_LONG, true, R.color.overlay_light_90);
    }

    // -------------------------
    public void showShortY(final String tips)
    {
        showShortColor(tips, R.color.yellow_800);
    }

    public void showShortY(final int tips)
    {
        showShortColor(tips, R.color.yellow_800);
    }

    public void showLongY(final String tips)
    {
        showLongColor(tips, R.color.yellow_800);
    }

    public void showLongY(final int tips)
    {
        showLongColor(tips, R.color.yellow_800);
    }

    // -------------------------
    public void showShortR(final String tips)
    {
        showShortColor(tips, R.color.red_700);
    }

    public void showShortR(final int tips)
    {
        showShortColor(tips, R.color.red_700);
    }

    public void showLongR(final String tips)
    {
        showLongColor(tips, R.color.red_700);
    }

    public void showLongR(final int tips)
    {
        showLongColor(tips, R.color.red_700);
    }

    // -------------------------
    public void showShortColor(final String tips, @ColorRes int color)
    {
        showToast(tips, -1, Toast.LENGTH_SHORT, true, color);
    }

    public void showShortColor(final int tips, @ColorRes int color)
    {
        showToast(null, tips, Toast.LENGTH_SHORT, true, color);
    }

    public void showLongColor(final String tips, @ColorRes int color)
    {
        showToast(tips, -1, Toast.LENGTH_LONG, true, color);
    }

    public void showLongColor(final int tips, @ColorRes int color)
    {
        showToast(null, tips, Toast.LENGTH_LONG, true, color);
    }

    /**
     * 显示Toast，多次调用此函数时，Toast显示的时间不会累计，并且显示内容为最后一次调用时传入的内容
     *
     * @param tips     要显示的内容
     * @param duration 持续时间，参见{@link Toast#LENGTH_SHORT}和
     *                 {@link Toast#LENGTH_LONG}
     */
    public void showToast(final String tips, final int tipi, final int duration, final boolean isCustom, @ColorRes final int color)
    {
        if (TextUtils.isEmpty(tips) && tipi <= 0)
        {
            return;
        }
        handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                if (mToast == null)
                {
                   initToast(
                            MQApp.getInstance(),
                            TextUtils.isEmpty(tips) ? MQApp.getInstance().getString(tipi) : tips,
                            duration,
                            isCustom,
                            color);
                } else
                {
                    if (isCustom)
                    {
                        text.setText(TextUtils.isEmpty(tips) ? MQApp.getInstance().getString(tipi) : tips);
                        if (color != 0)
                        {
                            bg.setCardBackgroundColor(MQApp.getInstance().getResources().getColor(color));
                        }
                    } else
                    {
                        mToast.setText(TextUtils.isEmpty(tips) ? MQApp.getInstance().getString(tipi) : tips);
                    }
                    mToast.setDuration(duration);
                }
                mToast.show();
            }
        });
    }


    private void initToast(Context context, String str, int duration, boolean isCustom, @ColorRes int color)
    {
        if (isCustom)
        {
            View layout = LayoutInflater.from(context).inflate(R.layout.toast_custom, null);
            text = layout.findViewById(R.id.text);
            bg = layout.findViewById(R.id.lyt_card);
            if (color != 0)
            {
                bg.setCardBackgroundColor(MQApp.getInstance().getResources().getColor(color));
            }
            text.setText(str);

            mToast = new Toast(MQApp.getInstance());
            mToast.setDuration(duration);
            mToast.setView(layout);
        } else
        {
            mToast = Toast.makeText(MQApp.getInstance(), str, duration);
        }
    }
}
