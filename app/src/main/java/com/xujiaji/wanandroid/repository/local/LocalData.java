package com.xujiaji.wanandroid.repository.local;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.App;
import com.xujiaji.wanandroid.helper.PrefHelper;
import com.xujiaji.wanandroid.helper.ToastHelper;
import com.xujiaji.wanandroid.repository.bean.Result;
import com.xujiaji.wanandroid.repository.bean.TreeBean;
import com.xujiaji.wanandroid.repository.remote.Net;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;

/**
 * author: xujiaji
 * created on: 2018/9/25 23:20
 * description:
 */

@Singleton
public class LocalData {
    private final Gson gson = new Gson();

    @Inject
    Lazy<Net> net;

    @Inject
    public LocalData() {}

    /**
     * 体系本地数据
     */
    public static final String POST_TREE_JSON = "tree/json";

    public void putTreeJsons(List<TreeBean> treeBeans) {
        String json = gson.toJson(treeBeans);
        PrefHelper.set(POST_TREE_JSON, json);
    }

    public List<TreeBean> getAllTreeBeans() {
        String json = PrefHelper.getString(POST_TREE_JSON);
        if (TextUtils.isEmpty(json)) {
            net.get().getPostTree().observeForever(new Observer<Result<List<TreeBean>>>() {
                @Override
                public void onChanged(@Nullable Result<List<TreeBean>> listResult) {
                    if (listResult == null || listResult.getErrorCode() != Net.ZERO) {
                        return;
                    }
                    putTreeJsons(listResult.getData());
                }
            });
            ToastHelper.info(App.getInstance().getString(R.string.start_init_tree_data));
            return null;
        }
        return gson.fromJson(json, new TypeToken<List<TreeBean>>() {}.getType());
    }

    public TreeBean getTreeBeans(int id) {
        List<TreeBean> treeBeans = getAllTreeBeans();
        if (treeBeans == null) return null;
        for (TreeBean tb : treeBeans) {
            List<TreeBean> tbs = tb.getChildren();
            for (TreeBean t : tbs) {
                if (t.getId() == id) {
                    tb.setCheckedChild(t);
                    return tb;
                }
            }
        }
        return null;
    }
}
