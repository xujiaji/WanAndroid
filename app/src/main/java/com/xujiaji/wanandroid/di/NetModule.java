package com.xujiaji.wanandroid.di;

import android.text.TextUtils;

import com.xujiaji.wanandroid.BuildConfig;
import com.xujiaji.wanandroid.helper.PrefHelper;
import com.xujiaji.wanandroid.repository.remote.API;
import com.xujiaji.wanandroid.repository.remote.Net;
import com.xujiaji.wanandroid.util.NetUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * author: xujiaji
 * created on: 2018/8/12 1:37
 * description:
 */
@Module
public class NetModule {

    @Singleton
    @Provides
    static API provideAPI(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
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
}
