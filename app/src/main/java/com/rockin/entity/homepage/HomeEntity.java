package com.rockin.entity.homepage;

import com.rockin.entity.table.Author;
import com.rockin.entity.table.PlayInfo;
import com.rockin.entity.table.Video;

import java.io.Serializable;

/**
 * 首页视频需要的数据
 */
public class HomeEntity implements Serializable {

    private Video video;
    private Author author;
    private PlayInfo highPlayInfo;
    private PlayInfo normalPlayInfo;

    public HomeEntity() {
    }

    public HomeEntity(Video video, Author author, PlayInfo highPlayInfo, PlayInfo normalPlayInfo) {
        this.video = video;
        this.author = author;
        this.highPlayInfo = highPlayInfo;
        this.normalPlayInfo = normalPlayInfo;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public PlayInfo getHighPlayInfo() {
        return highPlayInfo;
    }

    public void setHighPlayInfo(PlayInfo highPlayInfo) {
        this.highPlayInfo = highPlayInfo;
    }

    public PlayInfo getNormalPlayInfo() {
        return normalPlayInfo;
    }

    public void setNormalPlayInfo(PlayInfo normalPlayInfo) {
        this.normalPlayInfo = normalPlayInfo;
    }
}