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

import com.xujiaji.wanandroid.module.login.LoginViewModel;
import com.xujiaji.wanandroid.module.main.fragment.boxes.MainBoxesViewModel;
import com.xujiaji.wanandroid.module.main.fragment.friend_link.FriendLinkViewModel;
import com.xujiaji.wanandroid.module.main.fragment.openapis.OpenAPISViewModel;
import com.xujiaji.wanandroid.module.main.fragment.post_tree.PostTreeViewModel;
import com.xujiaji.wanandroid.module.main.fragment.posts.MainBlogPostsViewModel;
import com.xujiaji.wanandroid.module.main.fragment.project_category.ProjectCategoryViewModel;
import com.xujiaji.wanandroid.module.main.fragment.projects.MainProjectsViewModel;
import com.xujiaji.wanandroid.module.main.fragment.web_nav.WebNavViewModel;
import com.xujiaji.wanandroid.module.read.ReadViewModel;

import dagger.Lazy;
import dagger.Subcomponent;

/**
 * author: xujiaji
 * created on: 2018/6/12 13:20
 * description:
 */
@Subcomponent
public interface ViewModelSubComponent
{
    @Subcomponent.Builder
    interface Builder
    {
        ViewModelSubComponent build();
    }

    Lazy<MainBlogPostsViewModel> viewModelMainBlogPosts();
    Lazy<MainProjectsViewModel> viewModelMainProjects();
    Lazy<MainBoxesViewModel> viewModelMainBoxes();
    Lazy<ReadViewModel> viewModelRead();
    Lazy<LoginViewModel> viewModelLogin();
    Lazy<OpenAPISViewModel> viewModelOpenAPIS();
    Lazy<PostTreeViewModel> viewModelPostTree();
    Lazy<WebNavViewModel> viewModelWebNav();
    Lazy<FriendLinkViewModel> viewModelFriendLink();
    Lazy<ProjectCategoryViewModel> viewModelProjectCategory();
}
