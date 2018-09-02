package com.xujiaji.wanandroid.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.helper.ViewHelper;
import com.xujiaji.wanandroid.module.main.fragment.post_tree.PostTreeViewModel;
import com.xujiaji.wanandroid.repository.bean.TreeBean;

/**
 * author: xujiaji
 *
 * created on: 2018/8/19 18:26
 * description:
 */
public class FlexboxBindingAdapter {

    @BindingAdapter(value = {"fullFlexData", "postTreeViewModel"})
    public static void fullFlexData(FlexboxLayout layout, TreeBean treeBean, PostTreeViewModel viewModel) {
        layout.removeAllViews();
        if (treeBean.getChildren() == null || treeBean.getChildren().size() == 0) return;
        Context context = layout.getContext();
        for (TreeBean tree : treeBean.getChildren()) {
            TextView textView = new TextView(context);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewHelper.dpToPx(context, 40)));
            textView.setTextColor(ContextCompat.getColor(context, R.color.textSecondary));
            textView.setBackgroundResource(R.drawable.btn_rounded_outline);
            textView.setGravity(Gravity.CENTER);
            textView.setFocusable(true);
            textView.setClickable(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setForeground(ContextCompat.getDrawable(context, R.drawable.ripple_theme_small));
            }
            textView.setText(tree.getName());
            textView.setOnClickListener(v -> viewModel.mTagClickEvent.setValue(tree));
            layout.addView(textView);
        }
    }

}
