package com.xujiaji.wanandroid.module.category_detail;

import com.xujiaji.mvvmquick.di.FragmentScoped;
import com.xujiaji.wanandroid.module.main.fragment.posts.MainBlogPostsFragment;
import com.youth.banner.Banner;

import dagger.BindsOptionalOf;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * author: xujiaji
 * created on: 2018/9/15 12:41
 * description:
 */
@Module
public abstract class CategoryDetailModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainBlogPostsFragment contributeMainBlogPostsFragment();


    @Provides
    public static MainBlogPostsFragment provideBlogModel() {
        return new MainBlogPostsFragment();
    }

    @BindsOptionalOf
    public abstract Banner optionalBanner();

}
