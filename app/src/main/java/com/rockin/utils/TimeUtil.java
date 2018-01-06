package com.rockin.utils;

import java.text.SimpleDateFormat;

/**
 * Created by Penoder on 2017/8/26.
 */
public class TimeUtil {

    private TimeUtil() {
        throw new UnsupportedOperationException("TimeUtil can't be instantiated");
    }

    /**
     * 将 long 类型的时间戳 转换成 String 类型的时间格式
     *
     * @param unix   需要转换的时间戳
     * @param format 时间格式（默认 yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static String unixToDate(long unix, String format) {
        if (format == null || "".equals(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(unix);
    }

    public static String unixToDate(long unix) {
        return unixToDate(unix, null);
    }

    public static long dateToUnix(String date) {
        return dateToUnix(date, null);
    }

    /**
     * 时间转时间戳
     *
     * @param date   需要转换的时间
     * @param format 时间格式（默认 yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static long dateToUnix(String date, String format) {
        if (format == null || "".equals(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        long unix = -1;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            unix = simpleDateFormat.parse(date).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unix;
    }


    /**
     * 将秒转换成Time形式
     *
     * @param time 单位为 s 的时间
     * @return
     */
    public static String secondToTime(int time) {
        // 计算小时
        int hour = time / 3600;
        // 计算分钟
        time = time % 3600;
        int min = time / 60;
        // 计算秒
        int sec = time % 60;

        String h = hour < 10 ? "0" + hour : hour + "";
        String m = min < 10 ? "0" + min : min + "";
        String s = sec < 10 ? "0" + sec : sec + "";

        String hms = s + "\"";
        if (hour <= 0) {    // 小时? 不存在的
            // 不管分钟数是不是为0,统一带上分钟'秒钟"的形式
            hms = m + "'" + hms;
        } else {
            hms = h + "'" + m + "'" + hms;
        }

        return hms;
    }
}
