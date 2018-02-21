package com.rockin.view.found.hot;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.penoder.mylibrary.banner.BGABanner;
import com.penoder.mylibrary.utils.LogUtil;
import com.penoder.mylibrary.utils.TimeUtil;
import com.rockin.R;
import com.rockin.adapter.CommonListAdapter;
import com.rockin.config.EyeApi;
import com.rockin.databinding.FragmentFoundHotBinding;
import com.rockin.entity.discovery.hot.HotBanner;
import com.rockin.entity.discovery.hot.HotEntity;
import com.rockin.entity.homepage.HomeEntity;
import com.rockin.entity.table.Author;
import com.rockin.entity.table.PlayInfo;
import com.rockin.entity.table.Video;
import com.rockin.view.base.BaseFragment;
import com.rockin.view.homepage.PlayerActivity;
import com.rockin.widget.CircleImageView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 开眼还禁止了状态栏点击回顶～～～
 *
 * @author Penoder
 * @date 2018/1/21.
 */
public class HotFragment extends BaseFragment {

    private Context mContext;

    private MyHandler mHandler;

    private FragmentFoundHotBinding hotBinding;

    private List<HomeEntity> hotVideos = new ArrayList<>();

    private CommonListAdapter<HomeEntity> hotAdapter;

    private List<HotBanner> bannerList = new ArrayList<>();

    /**
     * 用于判断获取的数据是不是到达了最新发布的时候，一般前面9条不是
     */
    private boolean isLatestVideo = false;

    /**
     * Banner
     */
    private BGABanner viewPagerHotBanner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mHandler = new MyHandler(this);
        loadVideo(0, 50);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        hotBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_found_hot, container, false);
        hotBinding.setViewModel(this);
        hotBinding.executePendingBindings();
        initAdapter();
        return hotBinding.getRoot();
    }

    /**
     * 初始化轮播
     */
    private void initBanner(View hotHeaderView) {
        viewPagerHotBanner = (BGABanner) hotHeaderView.findViewById(R.id.viewPager_hotBanner);
        ImageView imgViewWeekRank = (ImageView) hotHeaderView.findViewById(R.id.imgView_weekRank);
        ImageView imgViewMonthRank = (ImageView) hotHeaderView.findViewById(R.id.imgView_monthRank);
        ImageView imgViewTotalRank = (ImageView) hotHeaderView.findViewById(R.id.imgView_totalRank);
        ImageView imgViewAllRank = (ImageView) hotHeaderView.findViewById(R.id.imgView_allRank);
        imgViewWeekRank.setOnClickListener(v -> jumpToRank(1));
        imgViewMonthRank.setOnClickListener(v -> jumpToRank(2));
        imgViewTotalRank.setOnClickListener(v -> jumpToRank(3));
        imgViewAllRank.setOnClickListener(v -> jumpToRank(1));

        viewPagerHotBanner.setAdapter((banner, itemView, model, position) -> {
            if (model != null) {
                Glide.with(mContext).load(((HotBanner) model).image).placeholder(R.drawable.img_default_banner).centerCrop().dontAnimate().into((ImageView) itemView);
                itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(mContext, HotBannerActivity.class);
                    intent.putExtra("ACTION_TITLE", bannerList.get(position).title);
                    intent.putExtra("ACTION_URL", bannerList.get(position).actionUrl);
                    startActivity(intent);
                });
            }
        });
    }

    /**
     * 跳转到排行页面，i表示排行的分类
     * 1. 表示周排行、 2. 表示月排行、 3. 表示总排行
     *
     * @param i
     */
    private void jumpToRank(int i) {
        Intent intent = new Intent(mContext, RankActivity.class);
        intent.putExtra("RANK_TYPE", i);
        startActivity(intent);
    }

    private void initAdapter() {
        View hotHeaderView = LayoutInflater.from(mContext).inflate(R.layout.layout_discovery_hot_header, null);
        hotBinding.listViewHotVideo.addHeaderView(hotHeaderView);
        initBanner(hotHeaderView);
        hotAdapter = new CommonListAdapter<HomeEntity>(hotVideos, R.layout.item_home_page_video) {
            @Override
            public void onBindView(HomeEntity homeEntity, ViewHolder holder, int position) {

                FrameLayout frameFeedImg = holder.getView(R.id.frame_feedImg);
                ImageView imgViewFeed = holder.getView(R.id.imgView_feed);
                CircleImageView circleImgEyeAuslese = holder.getView(R.id.circleImg_eyeAuslese);
                LinearLayout linearJumpAuthor = holder.getView(R.id.linear_jumpAuthor);
                CircleImageView circleImgAuthorIcon = holder.getView(R.id.circleImg_authorIcon);
                TextView txtViewMainTitle = holder.getView(R.id.txtView_mainTitle);
                TextView txtViewSubTitle = holder.getView(R.id.txtView_subTitle);
                ImageView imgViewMoreOperate = holder.getView(R.id.imgView_moreOperate);
                TextView txtViewPublishTime = holder.getView(R.id.txtView_publishTime);

                circleImgEyeAuslese.setVisibility(View.GONE);  // 开眼精选下标
                imgViewMoreOperate.setVisibility(View.GONE);    // 更多操作图标
                txtViewPublishTime.setVisibility(View.VISIBLE);
                if (homeEntity.getVideo() != null && homeEntity.getAuthor() != null) {
                    if (imgViewFeed != null) {
                        if (!(homeEntity.getVideo().feed + position).equals(imgViewFeed.getTag(imgViewFeed.getId()))) {
                            Glide.with(mContext).load(homeEntity.getVideo().feed).placeholder(R.drawable.img_default_eyepetizer).into(imgViewFeed);
                            imgViewFeed.setTag(imgViewFeed.getId(), homeEntity.getVideo().feed + position);
                        }
                    }
                    txtViewMainTitle.setText(homeEntity.getVideo().title);
                    txtViewSubTitle.setText(homeEntity.getVideo().slogan);
                    if (circleImgAuthorIcon != null) {
                        if (!(homeEntity.getAuthor().icon + position).equals(circleImgAuthorIcon.getTag(circleImgAuthorIcon.getId()))) {
                            Glide.with(mContext).load(homeEntity.getAuthor().icon).placeholder(R.drawable.icon_default_head).into(circleImgAuthorIcon);
                            circleImgAuthorIcon.setTag(circleImgAuthorIcon.getId(), homeEntity.getAuthor().icon + position);
                        }
                    }
                    txtViewSubTitle.setText(homeEntity.getAuthor().name + " / " + TimeUtil.secondToTime(homeEntity.getVideo().duration));
                    // 后面根据视频的时间戳与现在的时间戳比较得到多少时间前发布的
                    txtViewPublishTime.setText(TimeUtil.compareTime(homeEntity.getVideo().date * 1000));

                    frameFeedImg.setOnClickListener(v -> jumpToPlayer(homeEntity));
                    linearJumpAuthor.setOnClickListener(v -> jumpToAuthor());
                }
            }
        };
        hotBinding.listViewHotVideo.setAdapter(hotAdapter);
    }

    /**
     * 发现页加载的视频数据需要 40 条，并且是从 最新发布之后的 40 条
     */
    private void loadVideo(int pageNum, int pageSize) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(EyeApi.DISCOVERY_HOT + "?start=" + pageNum + "&num=" + pageSize).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.i("Rocoder：failure：获取热门视频数据失败------");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonStr = "";
                try {
                    jsonStr = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mHandler.obtainMessage(0, jsonStr).sendToTarget();
            }
        });
    }

    /**
     * 跳转到作者信息界面
     */
    private void jumpToAuthor() {

    }

    /**
     * 最新发布的视频的Item的点击事件
     */
    private void jumpToPlayer(HomeEntity homeEntity) {
        Intent intent = new Intent(mContext, PlayerActivity.class);
        intent.putExtra("VIDEO_DATA", homeEntity);
        startActivity(intent);
    }

    private static class MyHandler extends Handler {
        WeakReference<HotFragment> hotReference;

        MyHandler(HotFragment fragment) {
            hotReference = new WeakReference<HotFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    hotReference.get().parse((String) msg.obj);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 解析Json数据，
     * 解析得到的 Banner 地址，url 字段前面要 URL 解码，后面需要经过 Native/UTF-8解码
     */
    private void parse(String jsonStr) {
        LogUtil.i("Rocoder：parse：解析视频数据");
        if (jsonStr == null || "".equals(jsonStr.trim())) {
            LogUtil.d("热门视频数据获取到的Json数据是空的");
            return;
        }
        Gson gson = new Gson();
        HotEntity hotEntity = null;
        try {
            hotEntity = gson.fromJson(jsonStr, HotEntity.class);
        } catch (JsonSyntaxException e) {
            LogUtil.d("热门视频数据解析异常：" + e.getMessage());
        }
        if (hotEntity == null || hotEntity.getItemList() == null || hotEntity.getItemList().size() < 1) {
            return;
        }
        List<HotEntity.ItemEntity> itemList = hotEntity.getItemList();
        hotVideos.clear();
        for (int i = 0; i < itemList.size(); i++) {
            HotEntity.ItemEntity itemEntity = itemList.get(i);
            HomeEntity homeEntity = new HomeEntity();
            if ("video".equals(itemEntity.getType())) {
                // 现在只操作 type = video 的数据，其次还有 horizontalCard、Banner、TextHeader等数据类型
                HotEntity.ItemEntity.DataEntity videoEntity = itemEntity.getData();
                int videoId = videoEntity.getId(); // 视频ID（插入 video 表）
                String title = videoEntity.getTitle();  // 视频主标题
                String slogan = videoEntity.getSlogan();    // 视频副标题
                String description = videoEntity.getDescription();  // 视频简介
                String category = videoEntity.getCategory();        // 视频的种类标签
                String feed = videoEntity.getCover() != null ? videoEntity.getCover().getFeed() : "";     // 视频的预览封面
                String blurred = videoEntity.getCover() != null ? videoEntity.getCover().getBlurred() : "";     // 模糊化的视频的预览图
                String homepage = videoEntity.getCover() != null ? videoEntity.getCover().getHomepage() : "";   // 裁剪成正方形后的预览图，可用于分享时
                String playUrl = videoEntity.getPlayUrl();      // 视频的播放地址
                int duration = videoEntity.getDuration();   // 视频的时长，时间为秒s
                String webUrl = videoEntity.getWebUrl() != null ? videoEntity.getWebUrl().getRaw() : "";    // webView 加载的视频地址，用于分享链接
                long releaseTime = videoEntity.getReleaseTime();    // 视频的创建时间戳
                long date = videoEntity.getDate();  // 视频所属哪一天的时间戳，该时间一般对应某天的 9 点
                String type = videoEntity.getType();    // 视频类型
                int collectCount = videoEntity.getConsumption() != null ? videoEntity.getConsumption().getCollectionCount() : 0;    // 视频的收藏数量
                int sharedCount = videoEntity.getConsumption() != null ? videoEntity.getConsumption().getShareCount() : 0;      // 视频的分享次数
                int authorId = videoEntity.getAuthor() != null ? videoEntity.getAuthor().getId() : -1;      // 视频的作者ID，与 author 表进行关联的外键

                Video video = new Video(videoId, title, slogan, description, category, feed, blurred, homepage, playUrl, duration, webUrl, releaseTime, date, type, collectCount, sharedCount, authorId);
                if (isLatestVideo) {
                    homeEntity.setVideo(video);
                }
                // --------------------------- 以上为需要插入到 video 表中的数据--------------------------- //

                /**
                 * 往 author 表中插入数据
                 */
                if (videoEntity.getAuthor() != null) {
                    HotEntity.ItemEntity.DataEntity.AuthorEntity authorEntity = videoEntity.getAuthor();
                    int aId = authorEntity.getId();     // 作者 ID，唯一标识， 和 Video 表之间关联
                    String icon = authorEntity.getIcon();       // 作者头像
                    String name = authorEntity.getName();                   // 作者名称
                    String descriptionAuthor = authorEntity.getDescription();  // 作者简介，描述信息
                    String link = authorEntity.getLink();
                    long latestReleaseTime = authorEntity.getLatestReleaseTime();
                    int videoNum = authorEntity.getVideoNum();              // 作者发布的视频数量
                    int attentionNum = (int) (Math.random() * 1500 + 500);  // 作者被关注的人数，由于没有数据，所以随机 500 - 2000
                    int collectNum = (int) (Math.random() * 1500 + 500);    // 作者被收藏的次数，暂时不知道怎么收藏用户操作
                    int sharedNum = (int) (Math.random() * 1500 + 500);     // 作者被分享的次数， 同样随机生成 500 - 2000

                    Author author = new Author(aId, icon, name, descriptionAuthor, link, latestReleaseTime, videoNum, attentionNum, collectNum, sharedNum);
                    if (isLatestVideo) {
                        homeEntity.setAuthor(author);
                    }
                }

                /**
                 * 往 playInfo 表中插入数据
                 */
                if (videoEntity.getPlayInfo() != null && videoEntity.getPlayInfo().size() > 0) {
                    List<HotEntity.ItemEntity.DataEntity.PlayInfoEntity> playInfoEntityList = videoEntity.getPlayInfo();
                    for (HotEntity.ItemEntity.DataEntity.PlayInfoEntity playInfoEntity : playInfoEntityList) {
                        // playInfo 表 使用 videoId 与 video 表进行关联
                        int height = playInfoEntity.getHeight();    // 视频的高度
                        int width = playInfoEntity.getWidth();      // 视频宽度
                        String name = playInfoEntity.getName();     // 视频名称（标清或高清）
                        String playType = playInfoEntity.getType();     // 视频类型（normal Or high）
                        String aliyun = "";
                        String qcloud = "";
                        String ucloud = "";
                        if (playInfoEntity.getUrlList() != null && playInfoEntity.getUrlList().size() > 0) {
                            for (HotEntity.ItemEntity.DataEntity.PlayInfoEntity.UrlListEntity urlListEntity : playInfoEntity.getUrlList()) {
                                switch (urlListEntity.getName()) {
                                    case "aliyun":
                                        aliyun = urlListEntity.getUrl();
                                        break;
                                    case "qcloud":
                                        qcloud = urlListEntity.getUrl();
                                        break;
                                    case "ucloud":
                                        ucloud = urlListEntity.getUrl();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                        PlayInfo playInfo = new PlayInfo(videoId, height, width, name, playType, aliyun, qcloud, ucloud);
                        if (isLatestVideo) {
                            if ("normal".equals(playType)) {
                                homeEntity.setNormalPlayInfo(playInfo);
                            } else {
                                homeEntity.setHighPlayInfo(playInfo);
                            }
                        }
                    }
                }
                // 热门 广告位轮播
            } else if ("horizontalScrollCard".equals(itemEntity.getType())) {
                if (itemEntity.getData() != null) {
                    List<HotEntity.ItemEntity.DataEntity.ItemDataEntity> cardList = itemEntity.getData().getItemList();
                    if (cardList != null && cardList.size() > 0) {
                        bannerList.clear();
                        for (HotEntity.ItemEntity.DataEntity.ItemDataEntity cardEntity : cardList) {
                            HotEntity.ItemEntity.DataEntity.ItemDataEntity.BannerEntity bannerEntity = cardEntity.getData();
                            if (bannerEntity != null) {
                                String actionUrl = bannerEntity.getActionUrl();
                                String bannerTitle = "";
                                if (!TextUtils.isEmpty(actionUrl)) {
                                    bannerTitle = actionUrl.substring(actionUrl.indexOf("title=") + 6, actionUrl.indexOf("url=http") - 1);
                                    actionUrl = actionUrl.substring(actionUrl.indexOf("url=http") + 4);
                                }
                                // 需要 URLDecode 解码 http://blog.csdn.net/afgasdg/article/details/40304817
                                actionUrl = actionUrl.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
                                bannerTitle = bannerTitle.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
                                try {
                                    actionUrl = URLDecoder.decode(actionUrl, "UTF-8");
                                    bannerTitle = URLDecoder.decode(bannerTitle, "UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                HotBanner hotBanner = new HotBanner();
                                hotBanner.image = bannerEntity.getImage();
                                hotBanner.actionUrl = actionUrl;
                                hotBanner.title = bannerTitle;
                                bannerList.add(hotBanner);
                            }
                        }
                        viewPagerHotBanner.setData(bannerList, null);
                    }
                }
            }
            // 标题数据，当检测到 textHeader 为 最新发布 出现时，后面的视频数据才是需要的
            else if ("textHeader".equals(itemEntity.getType())) {
                String txtTitle = itemEntity.getData().getText();
                if ("最新发布".equals(txtTitle)) {
                    isLatestVideo = true;
                    continue;   // 调出当前循环，下一次才开始加入
                }
            }
            if (isLatestVideo) {
                hotVideos.add(homeEntity);
            }
            hotAdapter.notifyDataSetChanged();
        }
    }
}
