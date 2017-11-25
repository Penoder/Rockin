package com.penoder.mylibrary.okhttp;

/**
 * 用于前后台的json数据传递
 *
 * @param <T>
 * @author penoder
 * @date 17-11-24
 */
public class CommonJson<T> {

    /**
     * 请求接口的状态码，code=1时表示请求成功，否则请求失败
     */
    public int code;

    /**
     * 请求接口获取的消息提示，例如提示失败的原因等
     */
    public String message;

    /**
     * 真正用来传递的数据，可以是基本数据类型，数组、集合等
     */
    public T datas;
}
