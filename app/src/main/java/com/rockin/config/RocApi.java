package com.rockin.config;

/**
 * 所有的请求 URL 都在这
 *
 * @author asus
 * @date 2017/11/25
 */

public class RocApi {

    /**
     * 引导页面背景视频地址
     */
    public static final String GUIDE_BG_VIDEO = "http://47.100.7.114:8080/Graduation/videos/splash.mp4";

    /**
     * 后台地址
     */
    private static final String CLOSE_EYES = "http://192.168.1.105:8080/CloseEyes";

    public static final String TEST_SERVLET = CLOSE_EYES + "/test/TestServlet";

}
