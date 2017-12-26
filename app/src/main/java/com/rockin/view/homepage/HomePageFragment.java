package com.rockin.view.homepage;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.penoder.mylibrary.okhttp.OkHttpManager;
import com.rockin.R;
import com.rockin.adapter.CommonViewAdapter;
import com.rockin.config.RocApi;
import com.rockin.databinding.FragmentHomePageBinding;
import com.rockin.utils.LogUtil;
import com.rockin.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 首页 Banner 显示的 ImageView 的集合
     */
    List<View> bannerImgs = new ArrayList<>();

    /**
     * 显示在广告位上的图片 Url 集合
     */
    private List<String> bannerImgUrls = new ArrayList<>();

    /**
     * 广告位 主标题
     */
    private List<String> mainTitles = new ArrayList<>();

    /**
     * 广告位 副标题
     */
    private List<String> subsitles = new ArrayList<>();

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
     * 请求数据时的时间戳
     */
    private long dateLine;

    /**
     * 当前请求的页面数
     */
    private int pageNum;

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
        getDatas();
        initBanner();
        return homePageBinding.getRoot();
    }

    private void getDatas() {
        dateLine = System.currentTimeMillis();
        OkHttpManager.create(mContext)
                .addUrl(RocApi.HOME_PAGE)
                .post()
                .addParam("date", dateLine)
                .addParam("num", "2")
                .addParam("page", "");
    }

    /**
     * 初始化广告位
     */
    private void initBanner() {
        View bannerView = LayoutInflater.from(mContext).inflate(R.layout.layout_home_page_banner, null);
        FrameLayout frameHomeBanner = (FrameLayout) bannerView.findViewById(R.id.frame_homeBanner);
        viewParentBanner = (ViewPager) bannerView.findViewById(R.id.viewPager_homeBanner);
        linearIndicator = (LinearLayout) bannerView.findViewById(R.id.linear_indicator);
        tvMainTitle = (TextView) bannerView.findViewById(R.id.tv_mainTitle);
        tvSubTitle = (TextView) bannerView.findViewById(R.id.tv_subTitle);
        imgViewSearch = (ImageView) bannerView.findViewById(R.id.imgView_search);
        imgViewSearch.setOnClickListener(v -> Toast.makeText(mContext, "跳转到查询界面", Toast.LENGTH_SHORT).show());

        // 首先初始化 Banner 适配器,里面的值还是 0;所以在获取网络请求之后需要重新更新
        bannerAdapter = new CommonViewAdapter(bannerImgs) {
            @Override
            public void onBindView(ViewGroup container, View itemView, int position) {
                super.onBindView(container, itemView, position);
                Glide.with(mContext).load(bannerImgUrls.get(position)).placeholder(R.drawable.img_default_banner).into((ImageView) itemView);
            }
        };

        bannerImgUrls.add("https://cdn.dribbble.com/users/674925/screenshots/2858845/___1x.jpg");
        bannerImgUrls.add("https://cdn.dribbble.com/users/674925/screenshots/2920975/like_1x.jpg");
        bannerImgUrls.add("https://cdn.dribbble.com/users/674925/screenshots/2949037/image_1x.png");
        bannerImgUrls.add("https://cdn.dribbble.com/users/674925/screenshots/2940938/dabian_1x.jpg");
        bannerImgUrls.add("https://cdn.dribbble.com/users/674925/screenshots/3359811/img_7970_1x.jpg");

        for (int i = 0; i < bannerImgUrls.size(); i++) {
            ImageView imgView = new ImageView(mContext);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            imgView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            bannerImgs.add(imgView);

            ImageView imgViewIndicator = (ImageView) LayoutInflater.from(mContext)
                    .inflate(R.layout.img_banner_indicator, null).findViewById(R.id.imgView_indicator);
            imgViewIndicator.setSelected(false);
            linearIndicator.addView(imgViewIndicator);
        }
        viewParentBanner.setAdapter(bannerAdapter);
        // 设置默认选中项
        viewParentBanner.setCurrentItem(0);
        linearIndicator.getChildAt(0).setSelected(true);
        tvMainTitle.setText("这是主标题 1");
        tvSubTitle.setText("这是一个副标题 1");

        viewParentBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.i("当前的 position 为 " + position);
                for (int i = 0; i < bannerImgUrls.size(); i++) {
                    if (i == position) {
                        linearIndicator.getChildAt(position).setSelected(true);
                    } else {
                        linearIndicator.getChildAt(i).setSelected(false);
                    }
                }
                tvMainTitle.setText("这是主标题 " + (position + 1));
                tvSubTitle.setText("这是一个副标题 " + (position + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        homePageBinding.listViewHomePage.addHeaderView(bannerView);
        List<String> listData = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            listData.addAll(bannerImgUrls);
        }
        homePageBinding.listViewHomePage.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_expandable_list_item_1, android.R.id.text1, listData));
    }

    public void backToTop() {
        homePageBinding.listViewHomePage.setSelection(0);
    }
}
