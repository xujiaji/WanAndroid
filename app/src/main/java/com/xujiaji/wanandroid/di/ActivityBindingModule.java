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

package com.xujiaji.wanandroid.di;


import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.wanandroid.module.about.AboutActivity;
import com.xujiaji.wanandroid.module.category_detail.CategoryDetailActivity;
import com.xujiaji.wanandroid.module.category_detail.CategoryDetailModule;
import com.xujiaji.wanandroid.module.license.LicenseActivity;
import com.xujiaji.wanandroid.module.like.LikeActivity;
import com.xujiaji.wanandroid.module.login.LoginActivity;
import com.xujiaji.wanandroid.module.login.LoginModule;
import com.xujiaji.wanandroid.module.main.MainActivity;
import com.xujiaji.wanandroid.module.main.MainModule;
import com.xujiaji.wanandroid.module.main.fragment.posts.MainBlogPostsFragment;
import com.xujiaji.wanandroid.module.read.ReadActivity;
import com.xujiaji.wanandroid.module.read.ReadModule;
import com.xujiaji.wanandroid.module.set.SettingsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * author: xujiaji
 * created on: 2018/6/12 13:43
 * description:
 */
@Module
public abstract class ActivityBindingModule
{
    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeMainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = ReadModule.class)
    abstract ReadActivity contributeReadActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity contributeLoginActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract SettingsActivity contributeSettingsActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract LikeActivity contributeLikeActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract AboutActivity contributeAboutActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract LicenseActivity contributeLicenseActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = CategoryDetailModule.class)
    abstract CategoryDetailActivity contributeCategoryDetailActivity();

}
