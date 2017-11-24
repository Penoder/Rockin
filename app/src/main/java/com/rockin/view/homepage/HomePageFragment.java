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
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.rockin.R;
import com.rockin.adapter.CommonViewAdapter;
import com.rockin.rockin.databinding.FragmentHomePageBinding;
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
     * 首页显示 Banner 的 ViewPager
     */
    private ViewPager viewParentBanner;

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
        return homePageBinding.getRoot();
    }

    /**
     * 初始化广告位
     */
    private void initBanner() {
        // 首先初始化 Banner 适配器,里面的值还是 0;所以在获取网络请求之后需要重新更新
        bannerAdapter = new CommonViewAdapter(bannerImgs) {
            @Override
            public void onBindView(ViewGroup container, View itemView, int position) {
                super.onBindView(container, itemView, position);
                Glide.with(mContext).load(bannerImgUrls.get(position)).into((ImageView) itemView);
            }
        };

        bannerImgUrls.add("http://pic.jj20.com/up/allimg/911/021616153629/160216153629-1.jpg");
        bannerImgUrls.add("http://www.zhlzw.com/UploadFiles/Article_UploadFiles/201204/20120422013453408.JPG");
        bannerImgUrls.add("http://img.hb.aicdn.com/fe24b20f87398270e2b86096cc655e0978ae9d5f4d3cf-52xyz4_fw658");
        bannerImgUrls.add("http://img1.3lian.com/2015/a1/105/d/40.jpg");
        bannerImgUrls.add("http://img.hb.aicdn.com/743ade18bec55e29e8264344ffeabbb13ebd27481262ad-YvhQWR_fw658");
        for (int i = 0; i < bannerImgUrls.size(); i++) {
            ImageView imgView = new ImageView(mContext);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            imgView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            bannerImgs.add(imgView);
        }

        View bannerView = LayoutInflater.from(mContext).inflate(R.layout.layout_home_page_banner, null);
        viewParentBanner = (ViewPager) bannerView.findViewById(R.id.viewPager_homeBanner);
        viewParentBanner.getParent().requestDisallowInterceptTouchEvent(true);
        viewParentBanner.setAdapter(bannerAdapter);
        viewParentBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ListView listView = (ListView) homePageBinding.getRoot().findViewById(R.id.listView_homePage);
        listView.addHeaderView(bannerView);
        List<String> listData = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            listData.addAll(bannerImgUrls);
        }
        listView.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_expandable_list_item_1, android.R.id.text1, listData));
    }
}
