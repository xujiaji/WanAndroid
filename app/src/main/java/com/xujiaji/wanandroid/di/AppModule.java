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

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.xujiaji.mvvmquick.viewmodel.ProjectViewModelFactory;
import com.xujiaji.wanandroid.module.login.LoginViewModel;
import com.xujiaji.wanandroid.module.main.fragment.openapis.OpenAPISViewModel;
import com.xujiaji.wanandroid.module.main.fragment.post_tree.PostTreeViewModel;
import com.xujiaji.wanandroid.module.main.fragment.posts.MainBlogPostsViewModel;
import com.xujiaji.wanandroid.module.main.fragment.projects.MainProjectsViewModel;
import com.xujiaji.wanandroid.module.main.fragment.boxes.MainBoxesViewModel;
import com.xujiaji.wanandroid.module.read.ReadViewModel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

/**
 * author: xujiaji
 * created on: 2018/6/12 11:51
 * description:
 */
@Module(includes = NetModule.class, subcomponents = ViewModelSubComponent.class)
public abstract class AppModule {

    @Singleton
    @Provides
    static Map<Class<?>, Callable<Lazy<? extends ViewModel>>> providesViewModel(ViewModelSubComponent.Builder viewModelSubComponent) {
        ViewModelSubComponent vmsc = viewModelSubComponent.build();
        Map<Class<?>, Callable<Lazy<? extends ViewModel>>> creators = new HashMap<>();
        creators.put(MainBlogPostsViewModel.class, vmsc::viewModelMainBlogPosts);
        creators.put(MainProjectsViewModel.class, vmsc::viewModelMainProjects);
        creators.put(MainBoxesViewModel.class, vmsc::viewModelMainBoxes);

        creators.put(ReadViewModel.class, vmsc::viewModelRead);
        creators.put(LoginViewModel.class, vmsc::viewModelLogin);

        creators.put(OpenAPISViewModel.class, vmsc::viewModelOpenAPIS);
        creators.put(PostTreeViewModel.class, vmsc::viewModelPostTree);
        return creators;
    }

    @Singleton
    @Binds
    abstract ViewModelProvider.Factory provideViewModelFactory(ProjectViewModelFactory factory);
}
