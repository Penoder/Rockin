package com.penoder.mylibrary.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 实现 OkHttp 网络请求的简单封装
 *
 * @author asus
 * @date 2017/11/25
 */
public class OkHttpManager {

    /**
     * 定义该类的实例，方便其他方法的链式调用
     */
    private static OkHttpManager mInstance;

    /**
     * OkHttp 实例
     */
    private OkHttpClient okHttpClient;
    private Request.Builder requestBuilder;
    private FormBody.Builder bodyBuilder;

    /**
     * 用于将异步请求的回调回到主线程中
     */
    private Handler mHandler;

    private OkHttpManager() {
        okHttpClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpManager create() {
        if (mInstance == null) {
            synchronized (OkHttpManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 传递网络请求的 url
     *
     * @param url
     * @return
     */
    public OkHttpManager addUrl(String url) {
        requestBuilder = new Request.Builder().url(url);
        return this;
    }

    /**
     * OkHttp 的请求方式，调用则为 post 请求
     *
     * @return
     */
    public OkHttpManager post() {
        bodyBuilder = new FormBody.Builder();
        return this;
    }

    /**
     * post 请求传递的参数，可以增加相应的重载方法，实现不同类型的 value 传递
     *
     * @param key
     * @param value
     * @return
     */
    public OkHttpManager addParam(String key, String value) {
        if (bodyBuilder != null) {
            bodyBuilder.add(key, value);
        }
        return this;
    }

    /**
     * 表示开始执行网络请求
     *
     * @param okCallBack
     * @param object
     */
    public void execute(OkCallBack okCallBack, Object object) {
        Request request = null;
        if (requestBuilder == null) {
            return;
        }
        if (bodyBuilder != null) {
            request = requestBuilder.post(bodyBuilder.build()).build();
        } else {
            request = requestBuilder.build();
        }
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailure(call, e, okCallBack);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String jsonStr = "";
                    if (response != null && response.body() != null) {
                        jsonStr = response.body().string();
                        if (TextUtils.isEmpty(jsonStr)) {
                            sendResponse(okCallBack, false, response, object);
                        } else {
                            Gson gson = new Gson();
                            CommonJson<Object> commonJson = gson.fromJson(jsonStr, CommonJson.class);
                            if (commonJson != null && commonJson.code == 1) {
                                String dataJson = gson.toJson(commonJson.datas);

                                // 这里要做 Json 解析，需要考虑的情况有数组、集合、普通对象等,不知道该方式适不适用所有情况
                                Type type = new TypeToken<Object>() {
                                }.getType();
                                Object obj = gson.fromJson(dataJson, type);
                                sendResponse(okCallBack, true, response, obj);
                            } else {
                                sendResponse(okCallBack, false, response, object);
                            }
                        }
                    } else {
                        sendResponse(okCallBack, false, response, object);
                    }

                } catch (IOException | com.google.gson.JsonParseException e) {
                    sendFailure(call, e, okCallBack);
                }
            }
        });
    }

    private void sendFailure(Call call, Exception e, OkCallBack okCallBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                okCallBack.failure(call, e);
            }
        });
    }

    private void sendResponse(OkCallBack okCallBack, boolean isSuccess, Response response, Object obj) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                okCallBack.onResponse(isSuccess, response, obj);
            }
        });
    }


}
