package com.xujiaji.wanandroid.repository.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author: xujiaji
 * created on: 2018/8/31 10:37
 * description:
 */
public class VersionBean {

    /**
     * version_name : 1.0.0
     * version_code : 1
     * update_info : 更新信息
     * file_size : 5M
     * constraint : false
     * apk_url : https://github.com/xujiaji/HappyBubble/releases/download/v1.1.4-demoApk/app-debug.apk
     */

    @SerializedName("version_name")
    private String versionName;
    @SerializedName("version_code")
    private int versionCode;
    @SerializedName("update_info")
    private String updateInfo;
    @SerializedName("file_size")
    private String fileSize;
    @SerializedName("constraint")
    private boolean constraint;
    @SerializedName("apk_url")
    private String apkUrl;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public boolean isConstraint() {
        return constraint;
    }

    public void setConstraint(boolean constraint) {
        this.constraint = constraint;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }
}
