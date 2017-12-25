package com.rockin.config;

/**
 * 所有的请求 URL 都在这
 * https://raw.githubusercontent.com/jokermonn/-Api/master/Eyepetizer.md
 *
 * @author asus
 * @date 2017/11/25
 */

public class RocApi {

    /**
     * 首页内容的 URL, 和下面的 每日精选 接口返回的数据貌似一样
     * <p>
     * 拼接参数：
     * - `udid`：用户唯一标识。该参数可为空也可去除
     * - `vc`：???，固定值`168`。该参数可为空也可去除
     * - `vn`：客户端版本。该参数可为空也可去除
     * - `deviceModel`：手机信息。该参数可为空也可去除
     * - `first_channel`：???，固定值 `eyepetizer_baidu_market`。该参数可为空也可去除
     * - `last_channel`：???，固定值 `eyepetizer_baidu_market`。该参数可为空也可去除
     * - `system_version_code`：手机系统版本。该参数可为空也可去除
     * <p>
     * url 示例：[`http://baobab.kaiyanapp.com/api/v4/tabs/selected?udid=11111&vc=168&vn=3.3.1&deviceModel=Huawei%36&first_channel=eyepetizer_baidu_market&last_channel=eyepetizer_baidu_market&system_version_code=20`](http://baobab.kaiyanapp.com/api/v4/tabs/selected?udid=11111&vc=168&vn=3.3.1&deviceModel=Huawei%36&first_channel=eyepetizer_baidu_market&last_channel=eyepetizer_baidu_market&system_version_code=20)
     * 或 [`http://baobab.kaiyanapp.com/api/v4/tabs/selected`](http://baobab.kaiyanapp.com/api/v4/tabs/selected)
     */
    public static final String HOME_PAGE = "http://baobab.kaiyanapp.com/api/v4/tabs/selected";

    /**
     * 发现页面 API，返回的数据 分为 热门、分类、作者三部分，各对应一个 URL
     * <p>
     * 拼接参数：
     * - `udid`：用户唯一标识。该参数可为空也可去除
     * - `vc`：???，固定值`168`。该参数可为空也可去除
     * - `vn`：客户端版本。该参数可为空也可去除
     * - `deviceModel`：手机信息。该参数可为空也可去除
     * - `first_channel`：???，固定值 `eyepetizer_baidu_market`。该参数可为空也可去除
     * - `last_channel`：???，固定值 `eyepetizer_baidu_market`。该参数可为空也可去除
     * - `system_version_code`：手机系统版本。该参数可为空也可去除
     * <p>
     * url 示例：[`http://baobab.kaiyanapp.com/api/v4/discovery?udid=11111&vc=168&vn=3.3.1&deviceModel=Huawei%36&first_channel=eyepetizer_baidu_market&last_channel=eyepetizer_baidu_market&system_version_code=20`](http://baobab.kaiyanapp.com/api/v4/discovery?udid=11111&vc=168&vn=3.3.1&deviceModel=Huawei%36&first_channel=eyepetizer_baidu_market&last_channel=eyepetizer_baidu_market&system_version_code=20)
     * 或 [`http://baobab.kaiyanapp.com/api/v4/discovery`](http://baobab.kaiyanapp.com/api/v4/discovery)
     */
    public static final String DISCOVERY = "http://baobab.kaiyanapp.com/api/v4/discovery";

    /**
     * 关注模块 API
     * <p>
     * 拼接参数：
     * - `udid`：用户唯一标识。该参数可为空也可去除
     * - `vc`：???，固定值`168`。该参数可为空也可去除
     * - `vn`：客户端版本。该参数可为空也可去除
     * - `deviceModel`：手机信息。该参数可为空也可去除
     * - `first_channel`：???，固定值 `eyepetizer_baidu_market`。该参数可为空也可去除
     * - `last_channel`：???，固定值 `eyepetizer_baidu_market`。该参数可为空也可去除
     * - `system_version_code`：手机系统版本。该参数可为空也可去除
     * <p>
     * url 示例：[`http://baobab.kaiyanapp.com/api/v4/tabs/follow?udid=11111&vc=168&vn=3.3.1&deviceModel=Huawei%36&first_channel=eyepetizer_baidu_market&last_channel=eyepetizer_baidu_market&system_version_code=20`](http://baobab.kaiyanapp.com/api/v4/tabs/follow?udid=11111&vc=168&vn=3.3.1&deviceModel=Huawei%36&first_channel=eyepetizer_baidu_market&last_channel=eyepetizer_baidu_market&system_version_code=20)
     * 或 [`http://baobab.kaiyanapp.com/api/v4/tabs/follow`](http://baobab.kaiyanapp.com/api/v4/tabs/follow)
     */
    public static final String FOLLOW = "http://baobab.kaiyanapp.com/api/v4/tabs/follow";

    /*
     * -------------------------- 下面是一款仿 开眼 App 的接口  -------------------------
     */

    /**
     * 每日精选
     */
    public static final String DAILY = "http://baobab.wandoujia.com/api/v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";

    /**
     * 发现更多
     */
    public static final String FIND_MORE = "http://baobab.wandoujia.com/api/v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";

    /**
     * 热门排行
     */
    public static final String HOT_STRATEGY = "http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";

    /**
     * 发现更多详情接口
     */
    public static final String FIND_DETAIL = "http://baobab.wandoujia.com/api/v3/videos?categoryName=%s&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";


}
