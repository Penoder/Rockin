package com.rockin.config;

import android.app.Application;

/**
 * @author penoder
 * @date 17-11-24
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;

    /**
     * 记录当前网络状态，通过广播机制中监听网络来改变值
     */
    private int NETWORK_STATE;

    /**
     * 单例模式 获取 MyApplication 对象
     *
     * @return
     */
    public static MyApplication getInstance() {
        if (myApplication == null) {
            synchronized (MyApplication.class) {
                if (myApplication == null) {
                    myApplication = new MyApplication();
                }
            }
        }
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public int getNETWORK_STATE() {
        return NETWORK_STATE;
    }

    public void setNETWORK_STATE(int NETWORK_STATE) {
        this.NETWORK_STATE = NETWORK_STATE;
    }
}
