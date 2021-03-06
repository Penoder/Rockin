package com.rockin.view.homepage;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.penoder.mylibrary.okhttp.CommonJson;
import com.penoder.mylibrary.okhttp.OkCallBack;
import com.penoder.mylibrary.okhttp.OkHttpManager;
import com.penoder.mylibrary.player.JZVideoPlayer;
import com.penoder.mylibrary.player.JZVideoPlayerStandard;
import com.rockin.R;
import com.rockin.adapter.CommonListAdapter;
import com.rockin.config.EyeApi;
import com.rockin.databinding.ActivityPlayerBinding;
import com.rockin.entity.homepage.HomeEntity;
import com.rockin.entity.table.Author;
import com.rockin.entity.table.PlayInfo;
import com.rockin.entity.table.Video;
import com.penoder.mylibrary.utils.LogUtil;
import com.penoder.mylibrary.utils.NetUtils;
import com.penoder.mylibrary.utils.TimeUtil;
import com.penoder.mylibrary.utils.ToastUtil;
import com.rockin.view.base.BaseActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

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

    // 作者名称
    public ObservableField<String> authorName = new ObservableField<>();

    // 作者描述
    public ObservableField<String> authorDescription = new ObservableField<>();

    /**
     * 原本采用数据绑定的形式显示相关推荐列表，但是预览图显示有点问题，所以还是采用适配器试下
     */
    public ObservableList<HomeEntity> videoItems = new ObservableArrayList<>();
//    public ItemBinding videoItemView = ItemBinding.of(BR.viewModel, R.layout.item_recommend_video);

    private List<HomeEntity> recommendVideos = new ArrayList<>();
    private CommonListAdapter<HomeEntity> recommendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerBinding = DataBindingUtil.setContentView(this, R.layout.activity_player);
        playerBinding.setViewModel(this);

        getVideoDataAndInit();
        getRecommend();
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
            Glide.with(PlayerActivity.this).load(mVideo.feed).into(playerBinding.playerVideo.thumbImageView);

            // 判断网络，wifi自动播
            if (NetUtils.isWifi(this)) {
                playerBinding.playerVideo.startVideo();
            }
        }

        if (mAuthor != null) {
            authorName.set(mAuthor.name);
            authorDescription.set(mAuthor.description);
            if (!TextUtils.isEmpty(categoryDuration.get()) && !categoryDuration.get().contains("开眼精选")) {
                categoryDuration.set(categoryDuration.get() + (mAuthor.name.contains("精选") ? " / 开眼精选" : ""));
            }
            Glide.with(this).load(mAuthor.icon).into(playerBinding.circleImgVideoAuthor);
        }

        recommendAdapter = new CommonListAdapter<HomeEntity>(recommendVideos, R.layout.item_recommend_video) {
            @Override
            public void onBindView(HomeEntity homeEntity, ViewHolder holder, int position) {
                if (homeEntity != null && homeEntity.getVideo() != null) {
                    ImageView imgViewRecommendVideoFeed = holder.getView(R.id.imgView_recommendVideoFeed);
                    TextView txtViewRecommendTitle = holder.getView(R.id.txtView_recommendTitle);
                    TextView txtViewRecommendCategory = holder.getView(R.id.txtView_recommendCategory);
                    /*
                        遇到过 java.lang.IllegalArgumentException 非法参数异常，View 已经被回收了才调用 Glide 的 into（View）
                     */
                    if (imgViewRecommendVideoFeed != null) {
                        Glide.with(PlayerActivity.this).load(homeEntity.getVideo().feed).placeholder(R.drawable.img_default_eyepetizer).into(imgViewRecommendVideoFeed);
                    }
                    txtViewRecommendTitle.setText(homeEntity.getVideo().title);
                    txtViewRecommendCategory.setText(homeEntity.getVideo().category + " / " + TimeUtil.secondToTime(homeEntity.getVideo().duration));
                }
            }
        };
        playerBinding.listViewRecommend.setAdapter(recommendAdapter);
        playerBinding.listViewRecommend.setFocusable(false);    // 避免 ListView 抢占 ScrollView 的焦点
        playerBinding.listViewRecommend.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(PlayerActivity.this, PlayerActivity.class);
            intent.putExtra("VIDEO_DATA", recommendVideos.get(position));
            startActivity(intent);
            finish();
        });
    }

    /**
     * 获取相关推荐 30 条视频数据
     */
    private void getRecommend() {
        OkHttpManager.create(this)
                .addUrl(EyeApi.VIDEO_RECOMMEND)
                .post()
                .sign()
                .execute(new OkCallBack<String>() {
                    @Override
                    public void failure(Call call, Exception e) {
                        LogUtil.d("failure: " + e.getMessage());
                        ToastUtil.showShortToast(PlayerActivity.this, "获取数据失败,请稍候重试");
                    }

                    @Override
                    public void onResponse(boolean isSuccess, Response response, String jsonStr) {
                        LogUtil.i("视频播放页相关推荐数据： " + jsonStr);
                        if (TextUtils.isEmpty(jsonStr)) {
                            ToastUtil.showShortToast(PlayerActivity.this, "没有获取到数据,请稍候重试");
                        } else {
                            Gson gson = new Gson();
                            Type type = new TypeToken<CommonJson<List<HomeEntity>>>() {
                            }.getType();
                            CommonJson<List<HomeEntity>> commonJson = gson.fromJson(jsonStr, type);
                            if (commonJson != null && commonJson.code == 0) {
                                List<HomeEntity> homeEntityList = commonJson.data;
                                if (homeEntityList != null && homeEntityList.size() > 0) {
                                    recommendVideos.clear();
                                    recommendVideos.addAll(homeEntityList);
                                    videoItems.addAll(homeEntityList);
                                    recommendAdapter.notifyDataSetChanged();
                                    setListViewHeight(playerBinding.listViewRecommend);
//                                    for (int i = 0; i < homeEntityList.size(); i++) {
//                                        videoItems.add(new ItemVideoViewModel(homeEntityList.get(i), PlayerActivity.this));
//                                    }
                                } else {
                                    ToastUtil.showShortToast(PlayerActivity.this, "没有获取到数据,请稍候重试");
                                }
                            } else {
                                ToastUtil.showShortToast(PlayerActivity.this, "获取数据失败,请稍候重试");
                            }
                        }
                    }
                }, false);
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

    /**
     * 关注按钮点击事件
     */
    public ReplyCommand onAttentionCommand = new ReplyCommand(() -> {
    });

    /**
     * 作者信息点击事件，跳转到作者详情页
     */
    public ReplyCommand onAuthorInfoCommand = new ReplyCommand(() -> {
    });

    /**
     * 推荐的视频的 Item 点击事件, 跳转到自身
     */
//    public ReplyCommand<Integer> onRecommendItemCommand = new ReplyCommand<>((position) -> {
//        Intent intent = new Intent(this, PlayerActivity.class);
//        intent.putExtra("VIDEO_DATA", videoItems.get(position).mHomeEntity);
//        startActivity(intent);
//        finish();
//    });

    /**
     * 重写计算 ListView 高度，避免 ScrollView 嵌套 ListView 只显示 1 个 Item
     *
     * @param listView
     */
    public void setListViewHeight(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

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