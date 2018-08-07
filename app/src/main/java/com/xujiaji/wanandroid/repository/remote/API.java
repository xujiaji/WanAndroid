package com.xujiaji.wanandroid.repository.remote;

import com.xujiaji.wanandroid.repository.bean.BlogPostBean;
import com.xujiaji.wanandroid.repository.bean.PageBean;
import com.xujiaji.wanandroid.repository.bean.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author: xujiaji
 * created on: 2018/8/5 23:04
 * description:
 */
public interface API {

    @GET("article/list/{num}/json")
    Call<Result<PageBean<BlogPostBean>>> getBlogPosts(@Path("num") int num);

    @GET("article/listproject/{num}/json")
    Call<Result<PageBean<BlogPostBean>>> getPorjects(@Path("num") int num);
}
