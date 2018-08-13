package com.xujiaji.wanandroid.repository.bean;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author: xujiaji
 * created on: 2018/8/11 19:14
 * description:
 */
public class UserBean {

    /**
     * collectIds : [1011,3143,3224,3173,3234]
     * email :
     * icon :
     * id : 5603
     * password : pDVBxk2wRo
     * type : 0
     * username : jiajixu@qq.com
     */

    @SerializedName("email")
    private String email;
    @SerializedName("icon")
    private String icon;
    @SerializedName("id")
    private int id;
    @SerializedName("password")
    private String password;
    @SerializedName("type")
    private int type;
    @SerializedName("username")
    private String username;
    @SerializedName("collectIds")
    private List<Integer> collectIds;

    public String getEmail() {
        if (TextUtils.isEmpty(email)) return "Wan";
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        if (TextUtils.isEmpty(username)) return "Wan";
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }
}
