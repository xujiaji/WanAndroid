package com.xujiaji.wanandroid.repository.remote;

/**
 * author: xujiaji
 * created on: 2018/8/12 0:11
 * description:
 */
public interface DataCallback<T> {
    /**
     * 完成回调，不管成功还是失败
     */
    void finished();

    /**
     * 成功得到数据
     */
    void success(T bean);

    /**
     * 失败
     * @param code 错误码
     * @param msg 错误消息
     */
    void fail(int code, String msg);
}
