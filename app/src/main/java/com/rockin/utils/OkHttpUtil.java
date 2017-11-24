package com.rockin.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 封装 OkHttp 请求框架，采用 Builder 模式，方便链式调用
 *
 * @author penoder
 * @date 17-11-24
 */
public class OkHttpUtil {

    private static OkHttpUtil okHttpUtil;

    private static OkHttpClient okHttpClient;
    private Request.Builder builder;
    private FormBody.Builder bodyBuilder;

    /**
     * 传统使用方法
     */
    void method() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("", "")
                .build();
        Request request = new Request.Builder().url("").post(body).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    // 期望调用
    void method2() {
        OkHttpUtil.create()
                .addUrl("")
                .post()
                .addParam("", "")
                .callback();
    }

    /**
     * 初始化工具类对象 以及 OkHttpClient 对象
     *
     * @return
     */
    public static OkHttpUtil create() {

        if (okHttpUtil == null) {
            synchronized (OkHttpUtil.class) {
                if (okHttpUtil == null) {
                    okHttpUtil = new OkHttpUtil();
                }
            }
        }

        if (okHttpClient == null) {
            synchronized (OkHttpUtil.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                }
            }
        }
        return okHttpUtil;
    }

    /**
     * 添加请求的 Url 地址
     */
    private OkHttpUtil addUrl(String url) {
        if (builder == null) {
            builder = new Request.Builder();
        }
        builder.url(url);

        return this;
    }

    /**
     * 调用该方法表示采用 post 方式请求，否则为 get 请求
     *
     * @return
     */
    private OkHttpUtil post() {
        if (bodyBuilder == null) {
            bodyBuilder = new FormBody.Builder();
        }

        return this;
    }

    /**
     * 采用 post 请求时传递的参数
     *
     * @param key
     * @param value
     * @return
     */
    private OkHttpUtil addParam(String key, String value) {
        bodyBuilder.add(key, value);

        return this;
    }

    /**
     * 将 Request 对象 和 RequestBody 对象都给 build 出来;
     * 并且回调请求成功或失败的状态
     */
    private void callback() {
        Request request = null;
        if (bodyBuilder != null && builder != null) {
            RequestBody body = bodyBuilder.build();
            request = builder.post(body).build();
        } else if (builder != null) {
            request = builder.build();
        }
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
