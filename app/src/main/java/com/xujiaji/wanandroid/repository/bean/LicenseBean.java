package com.xujiaji.wanandroid.repository.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author: xujiaji
 * created on: 2018/8/30 19:22
 * description:
 */
public class LicenseBean {

    /**
     * thumb : https://avatars3.githubusercontent.com/u/1321838?s=400&v=4
     * name : Stream
     * author : aNNiMON
     * desc : Stream API from Java 8 rewritten on iterators for Java 7 and below
     * link : https://github.com/aNNiMON/Lightweight-Stream-API
     * license : Apache-2.0
     */

    @SerializedName("thumb")
    private String thumb;
    @SerializedName("name")
    private String name;
    @SerializedName("author")
    private String author;
    @SerializedName("desc")
    private String desc;
    @SerializedName("link")
    private String link;
    @SerializedName("license")
    private String license;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
