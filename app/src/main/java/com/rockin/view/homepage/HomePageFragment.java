package com.rockin.view.homepage;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.penoder.mylibrary.okhttp.CommonJson;
import com.penoder.mylibrary.okhttp.OkCallBack;
import com.penoder.mylibrary.okhttp.OkHttpManager;
import com.rockin.R;
import com.rockin.adapter.CommonListAdapter;
import com.rockin.adapter.CommonViewAdapter;
import com.rockin.config.EyeApi;
import com.rockin.databinding.FragmentHomePageBinding;
import com.rockin.entity.homepage.HomeEntity;
import com.rockin.entity.table.Author;
import com.rockin.entity.table.Video;
import com.rockin.utils.LogUtil;
import com.rockin.utils.TimeUtil;
import com.rockin.utils.ToastUtil;
import com.rockin.view.base.BaseFragment;
import com.rockin.widget.CircleImageView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 首页 Fragment
 *
 * @author Penoder
 * @date 2017/11/16.
 */
public class HomePageFragment extends BaseFragment {

    private Context mContext;

    private FragmentHomePageBinding homePageBinding;

    /**
     * 首页 Banner 的通用适配器
     */
    private CommonViewAdapter bannerAdapter;

    private CommonListAdapter<HomeEntity> videoAdapter;

    /**
     * 首页 Banner 显示的 ImageView 的集合
     */
    List<View> bannerImgs = new ArrayList<>();

    /**
     * 首页显示 Banner 的 ViewPager
     */
    private ViewPager viewParentBanner;

    /**
     * 显示 ViewPager 的指示器
     */
    private LinearLayout linearIndicator;

    /**
     * 首页广告位的主标题
     */
    private TextView tvMainTitle;

    /**
     * 首页广告位的副标题
     */
    private TextView tvSubTitle;

    /**
     * 搜索按钮，跳转到 SearchActivity
     */
    private ImageView imgViewSearch;

    /**
     * 更多开眼编辑精选
     */
    private LinearLayout linearMoreVideo;

    /**
     * 视频列表的数据
     */
    private List<HomeEntity> videoDatas = new ArrayList<>();

    /**
     * 广告位的视频数据
     */
    private List<HomeEntity> bannerDatas = new ArrayList<>();

    /**
     * 判断当前是刷新数据还是加载数据
     */
    private boolean isLoading = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homePageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false);
        homePageBinding.setViewModel(this);
        // 这个写在setViewModel前面会崩掉
        homePageBinding.executePendingBindings();
        initBanner();
        getVideoDatas();
        return homePageBinding.getRoot();
    }

    /**
     * 初始化广告位 和 列表
     */
    private void initBanner() {
        View bannerView = LayoutInflater.from(mContext).inflate(R.layout.layout_home_page_banner, null);
        viewParentBanner = (ViewPager) bannerView.findViewById(R.id.viewPager_homeBanner);
        linearIndicator = (LinearLayout) bannerView.findViewById(R.id.linear_indicator);
        tvMainTitle = (TextView) bannerView.findViewById(R.id.tv_mainTitle);
        tvSubTitle = (TextView) bannerView.findViewById(R.id.tv_subTitle);
        imgViewSearch = (ImageView) bannerView.findViewById(R.id.imgView_search);
        linearMoreVideo = (LinearLayout) bannerView.findViewById(R.id.linear_moreVideo);

        imgViewSearch.setOnClickListener(v -> Toast.makeText(mContext, "跳转到查询界面", Toast.LENGTH_SHORT).show());

        homePageBinding.listViewHomePage.addHeaderView(bannerView);

        // 首先初始化 Banner 适配器,里面的值还是 0;所以在获取网络请求之后需要重新更新
        bannerAdapter = new CommonViewAdapter(bannerImgs);
        viewParentBanner.setAdapter(bannerAdapter);

        viewParentBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < bannerDatas.size(); i++) {
                    if (i == position) {
                        linearIndicator.getChildAt(position).setSelected(true);
                    } else {
                        linearIndicator.getChildAt(i).setSelected(false);
                    }
                }
                if (bannerDatas.get(position).getVideo() != null) {
                    tvMainTitle.setText(bannerDatas.get(position).getVideo().title);
                    tvSubTitle.setText(bannerDatas.get(position).getVideo().slogan);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        videoAdapter = new CommonListAdapter<HomeEntity>(videoDatas, R.layout.item_home_page_video) {

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
                txtViewPublishTime.setVisibility(View.GONE);
                imgViewMoreOperate.setVisibility(View.VISIBLE);

                Video video = homeEntity.getVideo();
                Author author = homeEntity.getAuthor();
                if (video != null) {
                    // 是否是开眼精选视频类型
                    circleImgEyeAuslese.setVisibility("PANORAMIC".equals(video.type) ? View.VISIBLE : View.GONE);
                    // 预览图
                    Glide.with(mContext).load(video.feed).placeholder(R.drawable.img_default_eyepetizer).into(imgViewFeed);
                    txtViewMainTitle.setText(video.title);
                }
                if (author != null) {
                    // 头像
                    Glide.with(mContext).load(author.icon).placeholder(R.drawable.icon_default_head).into(circleImgAuthorIcon);
                }
                if (video != null && author != null) {
                    txtViewSubTitle.setText(author.name + " / " + TimeUtil.secondToTime(video.duration));
                }
                // 点击跳转到视频播放页面
                frameFeedImg.setOnClickListener(v -> {
                    jumpToPlayer(homeEntity);
                });

                // 点击跳转到作者信息界面
                linearJumpAuthor.setOnClickListener(v -> {
                    jumpToAuthor(homeEntity);
                });

                // 更多操作
                imgViewMoreOperate.setOnClickListener(v -> {
                    moreOperate(homeEntity);
                });

            }
        };
        homePageBinding.listViewHomePage.setAdapter(videoAdapter);

        // 刷新数据
        homePageBinding.swipeHomePage.setOnFlushListener(() -> {
            isLoading = false;
            getVideoDatas();
        });

        // 加载数据
        homePageBinding.swipeHomePage.setOnLoadListener(() -> {
            isLoading = true;
            getVideoDatas();
        });

    }

    /**
     * 更多操作
     *
     * @param homeEntity
     */
    private void moreOperate(HomeEntity homeEntity) {

    }

    /**
     * 跳转到作者详情页
     *
     * @param homeEntity
     */
    private void jumpToAuthor(HomeEntity homeEntity) {

    }

    /**
     * 跳转到视频播放页
     *
     * @param homeEntity
     */
    private void jumpToPlayer(HomeEntity homeEntity) {
        Intent intent = new Intent(mContext, PlayerActivity.class);
        intent.putExtra("VIDEO_DATA", homeEntity);
        startActivity(intent);
    }

    private void setBanner() {
        bannerImgs.clear();
        linearIndicator.removeAllViews();
        for (int i = 0; i < bannerDatas.size(); i++) {
            ImageView imgView = new ImageView(mContext);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            imgView.setAdjustViewBounds(true);
            imgView.setBackgroundResource(R.color.backgroundColor);
            imgView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            bannerImgs.add(imgView);

            if (bannerDatas.get(i).getVideo() != null) {
                Glide.with(mContext).load(bannerDatas.get(i).getVideo().homepage).placeholder(R.drawable.img_default_banner).into(imgView);
            }

            // 广告位点击事件
            int finalI = i;
            imgView.setOnClickListener(v -> {
                jumpToPlayer(bannerDatas.get(finalI));
            });

            ImageView imgViewIndicator = (ImageView) LayoutInflater.from(mContext).inflate(R.layout.img_banner_indicator, null).findViewById(R.id.imgView_indicator);
            imgViewIndicator.setSelected(false);
            linearIndicator.addView(imgViewIndicator);
        }
        bannerAdapter.notifyDataSetChanged();
        // 有数据的时候设置可见
        linearMoreVideo.setVisibility(View.VISIBLE);
        // 设置默认选中项
        viewParentBanner.setCurrentItem(0);
        linearIndicator.getChildAt(0).setSelected(true);
        if (bannerDatas.get(0).getVideo() != null) {
            tvMainTitle.setText(bannerDatas.get(0).getVideo().title);
            tvSubTitle.setText(bannerDatas.get(0).getVideo().slogan);
        }
        bannerAdapter.notifyDataSetChanged();
    }

    private void getVideoDatas() {
        OkHttpManager.create(mContext)
                .addUrl(EyeApi.VIDEO_HOMEPAGE)
                .post()
                .sign()
                .addProgress("", "内容正在加载中，请稍后！")
                .addParam("pageSize", "")
                .execute(new OkCallBack<String>() {
                    @Override
                    public void failure(Call call, Exception e) {
                        LogUtil.d("failure: " + e.getMessage());
                        ToastUtil.showShortToast(mContext, "获取数据失败,请稍候重试");
                        flushOrLoadOver();
                    }

                    @Override
                    public void onResponse(boolean isSuccess, Response response, String jsonStr) {
                        if (TextUtils.isEmpty(jsonStr)) {
                            ToastUtil.showShortToast(mContext, "没有获取到数据,请稍候重试");
                        } else {
                            Gson gson = new Gson();
                            Type type = new TypeToken<CommonJson<List<HomeEntity>>>() {
                            }.getType();
                            CommonJson<List<HomeEntity>> commonJson = gson.fromJson(jsonStr, type);
                            if (commonJson != null && commonJson.code == 0) {
                                List<HomeEntity> homeEntityList = commonJson.data;
                                // 前面 5 条数据是给 Banner 的,后面的才是视频列表的
                                if (homeEntityList != null && homeEntityList.size() > 5) {
                                    bannerDatas.clear();
                                    bannerDatas.addAll(homeEntityList.subList(0, 5));
                                    setBanner();

                                    if (isLoading) {
                                        videoDatas.addAll(homeEntityList.subList(5, homeEntityList.size()));
                                        videoAdapter.notifyDataSetChanged();
                                    } else {
                                        videoDatas.clear();
                                        videoDatas.addAll(homeEntityList.subList(5, homeEntityList.size()));
                                        videoAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    ToastUtil.showShortToast(mContext, "没有获取到数据,请稍候重试");
                                }
                            } else {
                                ToastUtil.showShortToast(mContext, "获取数据失败,请稍候重试");
                            }
                        }
                        flushOrLoadOver();
                    }

                }, false);
    }

    private void flushOrLoadOver() {
        if (isLoading) {
            homePageBinding.swipeHomePage.setLoading(false);
        } else {
            homePageBinding.swipeHomePage.setFlushing(false);
        }
    }

    public void backToTop() {
        //
        homePageBinding.listViewHomePage.setSelection(0);
    }
}
