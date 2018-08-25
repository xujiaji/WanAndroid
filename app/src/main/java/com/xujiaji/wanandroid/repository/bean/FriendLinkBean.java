package com.xujiaji.wanandroid.repository.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author: xujiaji
 * created on: 2018/8/25 12:33
 * description:
 */
public class FriendLinkBean {

    /**
     * url : http://www.jowanxu.top/
     * title :
     * thumb : http://www.wanandroid.com/blogimgs/2c1d0ec6-092d-45e9-94c2-17d7a99dea0a.png
     */

    @SerializedName("url")
    private String url;
    @SerializedName("title")
    private String title;
    @SerializedName("thumb")
    private String thumb;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
