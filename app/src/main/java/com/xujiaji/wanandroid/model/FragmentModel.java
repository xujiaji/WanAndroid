package com.xujiaji.wanandroid.model;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.annimon.stream.Stream;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.base.BaseFragment;
import com.xujiaji.wanandroid.module.main.fragment.AccountDrawerFragment;
import com.xujiaji.wanandroid.module.main.fragment.MainBlogPostsFragment;
import com.xujiaji.wanandroid.module.main.fragment.MenuDrawerFragment;
import com.xujiaji.wanandroid.module.main.fragment.MainProjectsFragment;
import com.xujiaji.wanandroid.module.main.fragment.MainToolsFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * author: xujiaji
 * created on: 2018/8/4 0:13
 * description:
 */
public class FragmentModel {
    private String title;
    private BaseFragment fragment;

    public FragmentModel(String title, BaseFragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public BaseFragment getFragment() {
        return fragment;
    }

    @NotNull
    public static List<FragmentModel> buildForDrawer(@NotNull Context context) {
        return Stream.of(new FragmentModel(context.getString(R.string.menu), new MenuDrawerFragment()),
                new FragmentModel(context.getString(R.string.profile), new AccountDrawerFragment()))
                .toList();
    }

    @NotNull
    public static List<FragmentModel> buildForMain(@NotNull Context context) {
        return Stream.of(new FragmentModel(context.getString(R.string.blog_post), new MainBlogPostsFragment()),
                new FragmentModel(context.getString(R.string.project), new MainProjectsFragment()),
                new FragmentModel(context.getString(R.string.tool), new MainToolsFragment()))
                .toList();
    }
}
