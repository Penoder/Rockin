package com.penoder.mylibrary.okhttp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.penoder.mylibrary.R;

import java.io.IOException;
import java.lang.ref.WeakReference;
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

    private static WeakReference<Context> mWeakReference;

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

    /**
     * 网络请求加载中显示的 Dialog
     */
    private AlertDialog loadDialog;

    private OkHttpManager() {
        okHttpClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpManager create(Context mContext) {
        mWeakReference = new WeakReference<>(mContext);
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
     * 用于执行网络请求的时候 弹出一个 加载中
     *
     * @return
     */
    public OkHttpManager addProgress() {
        // 为了每次重新创建
        destroyDialog();

        loadDialog = new AlertDialog.Builder(mWeakReference.get())
                .setIcon(R.drawable.loading)
                .setMessage("内容加载中，请稍后!")
                .show();
        return this;
    }

    /**
     * 网络请求加载中的提示文字由自个定
     *
     * @param msg
     * @return
     */
    public OkHttpManager addProgress(String msg) {
        destroyDialog();


        if (TextUtils.isEmpty(msg)) {
            msg = "内容加载中，请稍后!";
        }
        loadDialog = new AlertDialog.Builder(mWeakReference.get())
                .setIcon(R.drawable.loading)
                .setMessage(msg)
                .show();
        return this;
    }

    /**
     * 是否可以取消掉显示的加载中 Dialog
     *
     * @param msg
     * @param cancelable
     * @return
     */
    public OkHttpManager addProgress(String msg, boolean cancelable) {
        // 为了每次重新创建
        destroyDialog();

        if (TextUtils.isEmpty(msg)) {
            msg = "内容加载中，请稍后!";
        }
        loadDialog = new AlertDialog.Builder(mWeakReference.get())
                .setIcon(R.drawable.loading)
                .setMessage(msg)
                .setCancelable(cancelable)
                .create();
        loadDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // 设置返回键可以取消
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                    return true;
                }
                return false;
            }
        });
        loadDialog.show();
        return this;
    }

    /**
     * 为了每次重新创建 Dialog,调用时先将其销毁掉
     */
    private void destroyDialog() {
        if (loadDialog != null) {
            loadDialog.dismiss();
            loadDialog = null;
        }
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
                if (loadDialog != null && loadDialog.isShowing()) {
                    loadDialog.dismiss();
                    loadDialog = null;
                }
                okCallBack.failure(call, e);
            }
        });
    }

    private void sendResponse(OkCallBack okCallBack, boolean isSuccess, Response response, Object obj) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (loadDialog != null && loadDialog.isShowing()) {
                    loadDialog.dismiss();
                    loadDialog = null;
                }
                okCallBack.onResponse(isSuccess, response, obj);
            }
        });
    }

}
