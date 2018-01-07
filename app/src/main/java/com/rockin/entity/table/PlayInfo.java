package com.rockin.entity.table;

import java.io.Serializable;

/**
 * PlayInfo 表
 */
public class PlayInfo implements Serializable {
    public int videoId;    // 对应 video 表中的 videoId，外键
    public int height;    // 视频的高度
    public int width;      // 视频宽度
    public String name;     // 视频名称（标清或高清）
    public String type;     // 视频类型（normal Or high）
    public String aliyun;      // aliyun 保存的视频链接
    public String qcloud;      // qcloud 保存的视频链接
    public String ucloud;      // ucloud 保存的视频链接

    public PlayInfo() {
    }

    public PlayInfo(int videoId, int height, int width, String name, String type, String aliyun, String qcloud, String ucloud) {
        this.videoId = videoId;
        this.height = height;
        this.width = width;
        this.name = name;
        this.type = type;
        this.aliyun = aliyun;
        this.qcloud = qcloud;
        this.ucloud = ucloud;
    }
}
