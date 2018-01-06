package com.penoder.mylibrary.okhttp;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 作为 OkHttp 请求的回调，提供一个失败和一个成功的方法
 *
 * @param <T> 泛型类表示网络请求得到的数据类型
 * @author asus
 * @date 2017/11/25
 */
public interface OkCallBack<T> {

    /**
     * 表示网络请求失败
     *
     * @param call
     * @param e
     */
    void failure(Call call, Exception e);

    /**
     * 表示网络请求成功
     *
     * @param isSuccess 判断网络请求是否成功
     * @param response 响应的结果
     * @param data      json 解析后得到的数据类型
     */
    void onResponse(boolean isSuccess, Response response, T data);
}
