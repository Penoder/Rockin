package com.rockin.entity.table;

import java.io.Serializable;

/**
 * 对应数据库中的 author 表
 */
public class Author implements Serializable {
    public int authorId;     // 作者 ID，唯一标识， 和 Video 表之间关联
    public String icon;       // 作者头像
    public String name;                   // 作者名称
    public String description;  // 作者简介，描述信息
    public String link;
    public long latestReleaseTime;
    public int videoNum;              // 作者发布的视频数量
    public int attentionNum;  // 作者被关注的人数，由于没有数据，所以随机 500 - 2000
    public int collectNum;    // 作者被收藏的次数，暂时不知道怎么收藏用户操作
    public int sharedNum;     // 作者被分享的次数， 同样随机生成 500 - 2000

    public Author() {

    }

    public Author(int authorId, String icon, String name, String description, String link, long latestReleaseTime, int videoNum, int attentionNum, int collectNum, int sharedNum) {
        this.authorId = authorId;
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.link = link;
        this.latestReleaseTime = latestReleaseTime;
        this.videoNum = videoNum;
        this.attentionNum = attentionNum;
        this.collectNum = collectNum;
        this.sharedNum = sharedNum;
    }
}
