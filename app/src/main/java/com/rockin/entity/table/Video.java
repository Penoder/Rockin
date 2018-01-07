package com.rockin.entity.table;

import java.io.Serializable;

/**
 * Video 表所对应的字段
 */
public class Video implements Serializable{
    public int videoId;            // 视频ID（插入 video 表）
    public String title;           // 视频主标题
    public String slogan;          // 视频副标题
    public String description;     // 视频简介
    public String category;        // 视频的种类标签
    public String feed;            // 视频的预览封面
    public String blurred;         // 模糊化的视频的预览图
    public String homepage;        // 裁剪成正方形后的预览图，可用于分享时
    public String playUrl;         // 视频的播放地址
    public int duration;           // 视频的时长，时间为秒s
    public String webUrl;          // webView 加载的视频地址，用于分享链接
    public long releaseTime;       // 视频的创建时间戳
    public long date;              // 视频所属哪一天的时间戳，该时间一般对应某天的 9 点
    public String type;            // 视频类型
    public int collectCount;       // 视频的收藏数量
    public int sharedCount;        // 视频的分享次数
    public int authorId;           // 视频的作者ID，与 author 表进行关联的外键

    public Video() {

    }

    public Video(int videoId, String title, String slogan, String description, String category, String feed, String blurred, String homepage, String playUrl, int duration, String webUrl, long releaseTime, long date, String type, int collectCount, int sharedCount, int authorId) {
        this.videoId = videoId;
        this.title = title;
        this.slogan = slogan;
        this.description = description;
        this.category = category;
        this.feed = feed;
        this.blurred = blurred;
        this.homepage = homepage;
        this.playUrl = playUrl;
        this.duration = duration;
        this.webUrl = webUrl;
        this.releaseTime = releaseTime;
        this.date = date;
        this.type = type;
        this.collectCount = collectCount;
        this.sharedCount = sharedCount;
        this.authorId = authorId;
    }
}
