package com.rockin.entity.homepage;

/**
 * 首页视频需要的数据
 */
public class HomeEntity {

    // 视频 ID
    private int videoId;
    // 视频主标题
    private String title;
    // 视频副标题
    private String slogan;
    // 视频封面预览图
    private String feed;
    // 模糊化的封面预览图
    private String blurred;
    // 几乎裁剪成正方形的封面预览图
    private String homePage;
    // 用于分享的链接
    private String webUrl;
    // 视频播放地址
    private String playUrl;
    // 视频时长，单位 秒s
    private int duration;
    // 视频提供者的Id
    private int authorId;
    // 视频提供者的名字
    private String authorName;
    // 视频提供者的头像
    private String headIcon;

    public HomeEntity() {
    }

    public HomeEntity(int videoId, String title, String slogan, String feed, String blurred, String homePage, String webUrl, String playUrl, int duration, int authorId, String authorName, String headIcon) {
        this.videoId = videoId;
        this.title = title;
        this.slogan = slogan;
        this.feed = feed;
        this.blurred = blurred;
        this.homePage = homePage;
        this.webUrl = webUrl;
        this.playUrl = playUrl;
        this.duration = duration;
        this.authorId = authorId;
        this.authorName = authorName;
        this.headIcon = headIcon;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getBlurred() {
        return blurred;
    }

    public void setBlurred(String blurred) {
        this.blurred = blurred;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }
}
