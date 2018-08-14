package com.xujiaji.wanandroid.helper;

import android.content.Intent;

import com.xujiaji.mvvmquick.base.MQQuickAdapter;
import com.xujiaji.mvvmquick.util.LogUtil;
import com.xujiaji.wanandroid.module.read.ReadActivity;
import com.xujiaji.wanandroid.repository.bean.BlogPostBean;

import static android.app.Activity.RESULT_OK;

/**
 * author: xujiaji
 * created on: 2018/8/14 23:32
 * description:
 */
public class ActivityResultHelper {

    // 处理从阅读界面返回文章列表时的数据处理
    public static void handlePost(int requestCode, int resultCode, Intent data, MQQuickAdapter adapter) {
        if (requestCode == ReadActivity.ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            BlogPostBean postBean = data.getParcelableExtra(BlogPostBean.class.getSimpleName());
            if (postBean == null || adapter.getData().size() < postBean.getIndex()) return;
            LogUtil.e3("更新Item index = " + postBean.getIndex());
            adapter.getData().remove(postBean.getIndex());
            adapter.getData().add(postBean.getIndex(), postBean);
            adapter.notifyItemChanged(postBean.getIndex());
        }
    }
}
