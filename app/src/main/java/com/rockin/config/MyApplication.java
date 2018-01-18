package com.rockin.config;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        /**
         * bugly:
         *  appId = 'd16c72f77e'
         *  appKey = '7e8f2c93-6250-47db-912d-f758667d6535'
         */
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
//        ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
//            if (process.pid == android.os.Process.myPid()) {
//                processName = process.processName;
//            }
//        }
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(getApplicationContext(), "d16c72f77e", true);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public int getNETWORK_STATE() {
        return NETWORK_STATE;
    }

    public void setNETWORK_STATE(int NETWORK_STATE) {
        this.NETWORK_STATE = NETWORK_STATE;
    }
}
