package com.rockin.view.splash;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rockin.MainActivity;
import com.rockin.R;
import com.rockin.adapter.CommonViewAdapter;
import com.rockin.databinding.ActivityGuideBinding;
import com.rockin.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页，只需要在第一次进入 App 展示；
 * 主要是 4 个 ViewPager，背景为视频播放
 *
 * @author Penoder
 * @date 2017-11-17
 */
public class GuideActivity extends BaseActivity {

    private ActivityGuideBinding guideBinding;

    /**
     * ViewPager 的通用适配器
     */
    private CommonViewAdapter bannerAdapter;

    /**
     * 引导页切换的图片，透明化，无内容
     */
    private List<View> bannerImgs = new ArrayList<>(4);

    /**
     * 主标题中文数据
     */
    private List<String> mainTitles = new ArrayList<>(4);

    /**
     * 次标题英文数据
     */
    private List<String> subTitles = new ArrayList<>(4);

    /**
     * 当前滑动到的 position
     */
    private int currentPosition;

    /**
     * 记录用户按在屏幕上时的坐标（downX, downY）
     * 以及离开屏幕上时的坐标（upX, upY）
     */
    private float downX, downY, upX, upY;

    /**
     * 设备能识别到的最小滑动距离
     */
    private int minSlidePace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        guideBinding = DataBindingUtil.setContentView(this, R.layout.activity_guide);
        // 设置屏幕常亮，不会变暗也不会锁屏(http://blog.csdn.net/mrzhang628/article/details/50752392)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initVideoView();
        minSlidePace = ViewConfiguration.get(this).getScaledTouchSlop();
        initBanner();
    }

    /**
     * 既然 VideoView 采用 MediaPlayer 播放视频，那么不要 释放资源吗？里面处理好了？
     */
    private void initVideoView() {
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.guide_video;
        guideBinding.videoGuide.setVideoURI(Uri.parse(videoPath));
        guideBinding.videoGuide.start();
        guideBinding.videoGuide.setOnCompletionListener(mp -> guideBinding.videoGuide.start());
    }

    private void initBanner() {

        mainTitles.add("每日编辑精选，一如既往");
        mainTitles.add("关注越多，发现越多");
        mainTitles.add("离线自动缓存，精彩永不下线");
        mainTitles.add("登录即可订阅、评论和同步已收藏视频");

        subTitles.add("Daily appetizers for you eyes, as always");
        subTitles.add("Subscribe more, discover a whole lot more");
        subTitles.add("Enjoy your daily eyepetit, even without connectivity");
        subTitles.add("Sign into comment & collect video, to subscript also");

        // 首先初始化 Banner 适配器,里面的值还是 0;所以在获取网络请求之后需要重新更新
        bannerAdapter = new CommonViewAdapter(bannerImgs) {
            @Override
            public void onBindView(ViewGroup container, View itemView, int position) {
                super.onBindView(container, itemView, position);
            }
        };

        for (int i = 0; i < mainTitles.size(); i++) {
            ImageView imgView = new ImageView(this);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            imgView.setBackgroundColor(Color.TRANSPARENT);
            imgView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            bannerImgs.add(imgView);

            ImageView imgViewIndicator = (ImageView) LayoutInflater.from(this)
                    .inflate(R.layout.img_banner_indicator, null).findViewById(R.id.imgView_indicator);
            imgViewIndicator.setSelected(false);
            guideBinding.linearGuideIndicator.addView(imgViewIndicator);
        }
        guideBinding.viewPagerGuide.setAdapter(bannerAdapter);
        // 设置默认选中项
        guideBinding.viewPagerGuide.setCurrentItem(0);
        guideBinding.linearGuideIndicator.getChildAt(0).setSelected(true);
        guideBinding.tvGuideMainTitle.setText(mainTitles.get(0));
        guideBinding.tvGuideSubTitle.setText(subTitles.get(0));

        guideBinding.viewPagerGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                guideBinding.tvGuideMainTitle.setText(mainTitles.get(position));
                guideBinding.tvGuideSubTitle.setText(subTitles.get(position));
                for (int i = 0; i < mainTitles.size(); i++) {
                    if (i == position) {
                        guideBinding.linearGuideIndicator.getChildAt(position).setSelected(true);
                    } else {
                        guideBinding.linearGuideIndicator.getChildAt(i).setSelected(false);
                    }
                }
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 执行 引导页界面 底部的上滑引导箭头的 alpha 动画
        Animation upArrow1 = AnimationUtils.loadAnimation(this, R.anim.alpha_in_guide_up_arrows);
        Animation upArrow2 = AnimationUtils.loadAnimation(this, R.anim.alpha_in_guide_up_arrows);
        upArrow1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(() -> guideBinding.ivArrowUpTwo.startAnimation(upArrow2), 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        upArrow2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                guideBinding.ivArrowUpOne.startAnimation(upArrow1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        // 保留在变化后的一帧
        upArrow1.setFillAfter(true);
        upArrow2.setFillAfter(true);

        guideBinding.ivArrowUpTwo.startAnimation(upArrow2);

        /*
         * 监听界面的 滑动事件， 上滑和左滑 需要跳转到 MainActivity，
         * 并且不同方向的滑动， MainActivity 的启动动画还不一样
         */
        guideBinding.viewPagerGuide.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                //当手指按下的时候
                downX = event.getX();
                downY = event.getY();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                //当手指离开的时候
                upX = event.getX();
                upY = event.getY();

                // 表示上滑操作，并且 y轴 滑动距离 大于 2倍 x轴 滑动距离
                if (downY - upY > 6 * minSlidePace && Math.abs(upY - downY) > 2 * Math.abs(upX - downX)) {
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                    overridePendingTransition(R.anim.translate_from_bottom_to_up, 0);
                    new Handler().postDelayed(this::finish, 500);
                } else if (currentPosition == 3 && downX - upX > 6 * minSlidePace && Math.abs(upX - downX) > 2 * Math.abs(upY - downY)) {
                    // 左滑操作，并且 x轴 滑动距离 大于 2倍 y轴 滑动距离
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                    overridePendingTransition(R.anim.translate_from_right_to_left, 0);
                    new Handler().postDelayed(this::finish, 500);
                }
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        guideBinding.videoGuide.seekTo(playPosition);
        guideBinding.videoGuide.start();
    }

    private int playPosition;

    @Override
    protected void onStop() {
        super.onStop();
        guideBinding.videoGuide.pause();
        playPosition = guideBinding.videoGuide.getCurrentPosition();
    }
}
