package com.rockin.view.homepage;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.penoder.mylibrary.player.JZVideoPlayer;
import com.penoder.mylibrary.player.JZVideoPlayerStandard;
import com.rockin.R;
import com.rockin.databinding.ActivityPlayerBinding;
import com.rockin.entity.homepage.HomeEntity;
import com.rockin.entity.table.Author;
import com.rockin.entity.table.PlayInfo;
import com.rockin.entity.table.Video;
import com.rockin.utils.TimeUtil;
import com.rockin.view.base.BaseActivity;

/**
 * @author Penoder
 * @date 2017/01/07.
 */
public class PlayerActivity extends BaseActivity {

    private ActivityPlayerBinding playerBinding;

    /**
     * 视频相关的数据
     */
    private HomeEntity homeEntity;

    private Video mVideo;
    private Author mAuthor;
    private PlayInfo mNormalInfo;
    private PlayInfo mHighInfo;

    // 标题
    public ObservableField<String> videoTitle = new ObservableField<>();

    // 类型 + 时长 + 是否开眼精选
    public ObservableField<String> categoryDuration = new ObservableField<>();

    // 描述信息
    public ObservableField<String> videoDescription = new ObservableField<>();

    // 点赞数
    public ObservableField<String> heartCount = new ObservableField<>();

    // 分享数
    public ObservableField<String> sharedCount = new ObservableField<>();

    // 评论数,先写死为 0,以后加入登录系统再改
    public ObservableField<String> commentCount = new ObservableField<>("0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerBinding = DataBindingUtil.setContentView(this, R.layout.activity_player);
        playerBinding.setViewModel(this);

        getVideoDataAndInit();
    }

    /**
     * 接收传递的 Video 数据
     */
    private void getVideoDataAndInit() {
        homeEntity = (HomeEntity) getIntent().getSerializableExtra("VIDEO_DATA");
        if (homeEntity == null) {
            return;
        }
        mVideo = homeEntity.getVideo();
        mAuthor = homeEntity.getAuthor();
        mNormalInfo = homeEntity.getNormalPlayInfo();
        mHighInfo = homeEntity.getHighPlayInfo();

        if (mVideo != null) {
            Glide.with(this).load(mVideo.blurred).into(playerBinding.imgViewPlayBG);

            videoTitle.set(mVideo.title);
            categoryDuration.set("#" + mVideo.category + " / " + TimeUtil.secondToTime(mVideo.duration) + ("PANORAMIC".equals(mVideo.type) ? " / 开眼精选" : ""));
            videoDescription.set(mVideo.description);
            heartCount.set(mVideo.collectCount + "");
            sharedCount.set(mVideo.sharedCount + "");
            playerBinding.txtViewPlayDescription.setVisibility(View.GONE);
            playerBinding.imgViewUpDownDescription.setVisibility(View.VISIBLE);

            playerBinding.playerVideo.setUp(mVideo.playUrl, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
            playerBinding.playerVideo.thumbImageView.setImageURI(Uri.parse(mVideo.feed));
        }
    }

    /**
     * 未选中隐藏描述。选中展示描述信息
     */
    public ReplyCommand onShowDescriptionCommand = new ReplyCommand(() -> {
        playerBinding.imgViewUpDownDescription.setSelected(!playerBinding.imgViewUpDownDescription.isSelected());
        playerBinding.txtViewPlayDescription.setVisibility(playerBinding.imgViewUpDownDescription.isSelected() ? View.VISIBLE : View.GONE);
    });

    /**
     * 点赞事件
     */
    public ReplyCommand onHeartCommand = new ReplyCommand(() -> {
    });

    /**
     * 分享事件
     */
    public ReplyCommand onSharedCommand = new ReplyCommand(() -> {
    });

    /**
     * 评论事件
     */
    public ReplyCommand onCommentCommand = new ReplyCommand(() -> {
    });

    /**
     * 下载事件
     */
    public ReplyCommand onDownloadCommand = new ReplyCommand(() -> {
    });

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}
