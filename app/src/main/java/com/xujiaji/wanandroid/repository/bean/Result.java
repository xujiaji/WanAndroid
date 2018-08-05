package com.xujiaji.wanandroid.repository.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author: xujiaji
 * created on: 2018/8/5 23:16
 * description:
 */
public class Result<T> {

    /**
     * data : {}
     * errorCode : 0
     * errorMsg :
     */

    @SerializedName("data")
    private T data;
    @SerializedName("errorCode")
    private int errorCode;
    @SerializedName("errorMsg")
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


}
