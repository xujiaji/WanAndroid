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
import android.text.TextUtils;

import com.xujiaji.mvvmquick.viewmodel.ProjectViewModelFactory;
import com.xujiaji.wanandroid.BuildConfig;
import com.xujiaji.wanandroid.helper.PrefHelper;
import com.xujiaji.wanandroid.module.login.LoginActivity;
import com.xujiaji.wanandroid.module.login.LoginModule;
import com.xujiaji.wanandroid.module.login.LoginViewModel;
import com.xujiaji.wanandroid.module.main.fragment.posts.MainBlogPostsViewModel;
import com.xujiaji.wanandroid.module.main.fragment.projects.MainProjectsViewModel;
import com.xujiaji.wanandroid.module.read.ReadViewModel;
import com.xujiaji.wanandroid.repository.remote.API;
import com.xujiaji.wanandroid.repository.remote.Net;
import com.xujiaji.wanandroid.util.NetUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: xujiaji
 * created on: 2018/6/12 11:51
 * description:
 */
@Module(subcomponents = ViewModelSubComponent.class)
public abstract class AppModule {

    @Singleton
    @Provides
    static API provideAPI(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(API.class);
    }

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor,
                                            @Named("RequestInterceptor") Interceptor requestInterceptor,
                                            @Named("ResponseInterceptor") Interceptor responseInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(requestInterceptor)
                .addInterceptor(responseInterceptor)
                .connectTimeout(Net.TIME_OUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(Net.TIME_OUT_READ, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    @Named("ResponseInterceptor")
    static Interceptor provideResponseCookieInterceptor() {
        return chain -> {
            Request request = chain.request();
            Response response = chain.proceed(request);
            final String requestUrl = request.url().toString();

            if ((requestUrl.contains(Net.SAVE_USER_LOGIN_KEY) || requestUrl.contains(Net.SAVE_USER_REGISTER_KEY))
                    && !response.headers(Net.SET_COOKIE_KEY).isEmpty()) {
                PrefHelper.set(Net.SAVE_USER_LOGIN_KEY,
                        NetUtil.encodeCookie(
                                response.headers(Net.SET_COOKIE_KEY)));
            }
            return response;
        };
    }

    @Singleton
    @Provides
    @Named("RequestInterceptor")
    static Interceptor provideRequestCookieInterceptor() {
        return chain -> {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            String cookie = PrefHelper.getString(Net.SAVE_USER_LOGIN_KEY);
            if (!TextUtils.isEmpty(cookie)) {
                builder.addHeader(Net.COOKIE_NAME, cookie);
            }
            return chain.proceed(builder.build());
        };
    }

    @Singleton
    @Provides
    static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Singleton
    @Provides
    static Map<Class<?>, Callable<Lazy<? extends ViewModel>>> providesViewModel(ViewModelSubComponent.Builder viewModelSubComponent) {
        ViewModelSubComponent vmsc = viewModelSubComponent.build();
        Map<Class<?>, Callable<Lazy<? extends ViewModel>>> creators = new HashMap<>();
        creators.put(MainBlogPostsViewModel.class, vmsc::viewModelMainBlogPosts);
        creators.put(MainProjectsViewModel.class, vmsc::viewModelMainProjects);
        creators.put(ReadViewModel.class, vmsc::viewModelRead);
        creators.put(LoginViewModel.class, vmsc::viewModelLogin);
        return creators;
    }

    @Singleton
    @Binds
    abstract ViewModelProvider.Factory provideViewModelFactory(ProjectViewModelFactory factory);
}
