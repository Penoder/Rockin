package com.rockin.config;

import android.app.Application;

/**
 * @author penoder
 * @date 17-11-24
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;

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
}
