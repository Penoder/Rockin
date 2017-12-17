package com.rockin.view.splash;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

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

    private CommonViewAdapter bannerAdapter;

    private List<View> bannerImgs = new ArrayList<>(4);
    private List<String> mainTitles = new ArrayList<>(4);
    private List<String> subTitles = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        guideBinding = DataBindingUtil.setContentView(this, R.layout.activity_guide);
        // 设置屏幕常亮，不会变暗也不会锁屏(http://blog.csdn.net/mrzhang628/article/details/50752392)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initVideoView();
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
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
