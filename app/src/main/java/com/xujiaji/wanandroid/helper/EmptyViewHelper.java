package com.xujiaji.wanandroid.helper;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xujiaji.wanandroid.R;

/**
 * author: xujiaji
 * created on: 2018/8/25 22:59
 * description:
 */
public class EmptyViewHelper {

    public static void initEmpty(RecyclerView viewGroup) {
        if (viewGroup.getAdapter() instanceof BaseQuickAdapter) {
            ((BaseQuickAdapter) viewGroup.getAdapter()).setEmptyView(R.layout.no_item_archived, viewGroup);
        }
    }

    public static void setErrEmpty(RecyclerView viewGroup, String errInfo) {
        if (viewGroup.getAdapter() instanceof BaseQuickAdapter) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.no_item_error, viewGroup, false);
            TextView textView = view.findViewById(R.id.errInfo);
            if (TextUtils.isEmpty(errInfo)) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setText(errInfo);
            }
            ((BaseQuickAdapter) viewGroup.getAdapter()).setEmptyView(view);
        }
    }
}
