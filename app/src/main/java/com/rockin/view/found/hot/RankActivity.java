package com.rockin.view.found.hot;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.penoder.mylibrary.mvvm.bindingadapter.viewpager.ViewBindingAdapter;
import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.penoder.mylibrary.utils.DensityUtils;
import com.penoder.mylibrary.utils.ScreenUtils;
import com.rockin.R;
import com.rockin.adapter.CommonFragmentAdapter;
import com.rockin.databinding.ActivityRankBinding;
import com.rockin.view.base.BaseFragmentActivity;

/**
 * @author Penoder
 * @date 2018/2/21.
 */
public class RankActivity extends BaseFragmentActivity {

    private ActivityRankBinding rankBinding;

    /**
     * 接收上个页面传递过来的排行类型，
     * 1. 周排行
     * 2. 月排行
     * 3. 总排行
     */
    private int rankType;

    /**
     * 记录三个标签的居中位置，以及 选中的 指示器的 宽度
     */
    private float weekCenterX, monthCenterX, totalCenterX, indicatorHalfWidth;

    /**
     * 三个标签中间的相距的距离
     */
    float distance12, distance23;

    /**
     * 周排行、月排行、总排行三个页面
     */
    private RankFragment weekFragment, monthFragment, totalFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rankBinding = DataBindingUtil.setContentView(this, R.layout.activity_rank);
        rankBinding.setViewModel(this);
        rankType = getIntent().getIntExtra("RANK_TYPE", -1);
        initTabLocal();
        initFragment();
    }

    /**
     * 初始化标签的选择距离位置
     */
    private void initTabLocal() {
        int screenWidth = ScreenUtils.getScreenWidth(this);
        weekCenterX = screenWidth / 6;
        monthCenterX = screenWidth / 2;
        totalCenterX = screenWidth * 5 / 6;
        distance12 = monthCenterX - weekCenterX;
        distance23 = totalCenterX - monthCenterX;
        rankBinding.imgViewSelectedRank.measure(0, 0);
        indicatorHalfWidth = DensityUtils.px2dp(this, rankBinding.imgViewSelectedRank.getMeasuredWidth()) * 0.5F;
    }

    /**
     * 采用工厂模式初始化周排行、月排行、总排行三个Fragment
     */
    private void initFragment() {
        weekFragment = RankFragment.newInstance(1);
        monthFragment = RankFragment.newInstance(2);
        totalFragment = RankFragment.newInstance(3);
        rankBinding.viewPagerRank.setAdapter(new CommonFragmentAdapter(getSupportFragmentManager(), weekFragment, monthFragment, totalFragment));
        rankBinding.viewPagerRank.setCurrentItem(rankType - 1);
        rankBinding.viewPagerRank.setOffscreenPageLimit(2);
        switch (rankType) {
            case 1:
                rankBinding.imgViewSelectedRank.setX(weekCenterX - indicatorHalfWidth);
                rankBinding.txtViewWeekRank.setSelected(true);
                break;
            case 2:
                rankBinding.imgViewSelectedRank.setX(monthCenterX - indicatorHalfWidth);
                rankBinding.txtViewMonthRank.setSelected(true);
                break;
            case 3:
                rankBinding.imgViewSelectedRank.setX(totalCenterX - indicatorHalfWidth);
                rankBinding.txtViewTotalRank.setSelected(true);
                break;
            default:
                break;
        }
    }

    /**
     * 返回按钮点击事件
     */
    public ReplyCommand onBackCommand = new ReplyCommand(this::finish);

    /**
     * 周排行标签点击事件
     */
    public ReplyCommand onWeekRankCommand = new ReplyCommand(() -> {
        selectTab(0);
        rankBinding.viewPagerRank.setCurrentItem(0);
        rankBinding.imgViewSelectedRank.setX(weekCenterX - indicatorHalfWidth);
    });

    /**
     * 月排行标签点击事件
     */
    public ReplyCommand onMonthRankCommand = new ReplyCommand(() -> {
        selectTab(1);
        rankBinding.viewPagerRank.setCurrentItem(1);
        rankBinding.imgViewSelectedRank.setX(monthCenterX - indicatorHalfWidth);
    });

    /**
     * 总排行标签点击事件
     */
    public ReplyCommand onTotalRankCommand = new ReplyCommand(() -> {
        selectTab(2);
        rankBinding.viewPagerRank.setCurrentItem(2);
        rankBinding.imgViewSelectedRank.setX(totalCenterX - indicatorHalfWidth);
    });

    /**
     * 三个排行页面的切换事件
     */
    public ReplyCommand<ViewBindingAdapter.ViewPagerDataWrapper> onRankScrollCommand = new ReplyCommand<>((wrapper) -> {
        switch ((int) wrapper.position) {
            case 0:
                rankBinding.imgViewSelectedRank.setX(weekCenterX - indicatorHalfWidth + wrapper.positionOffset * (distance12));
                break;
            case 1:
                rankBinding.imgViewSelectedRank.setX(monthCenterX - indicatorHalfWidth + wrapper.positionOffset * (distance23));
                break;
            default:
                break;
        }
    });

    /**
     * 三个排行页面的选中事件
     */
    public ReplyCommand<Integer> onRankSelectedCommand = new ReplyCommand<>((position) -> {
        selectTab(position);
        float localX;
        if (position == 0) {
            localX = weekCenterX - indicatorHalfWidth;
        } else if (position == 1) {
            localX = monthCenterX - indicatorHalfWidth;
        } else {
            localX = totalCenterX - indicatorHalfWidth;
        }
        rankBinding.imgViewSelectedRank.setX(localX);
    });

    /**
     * 切换标签
     *
     * @param position
     */
    private void selectTab(int position) {
        rankBinding.txtViewWeekRank.setSelected(false);
        rankBinding.txtViewMonthRank.setSelected(false);
        rankBinding.txtViewTotalRank.setSelected(false);
        switch (position) {
            case 0:
                rankBinding.txtViewWeekRank.setSelected(true);
                break;
            case 1:
                rankBinding.txtViewMonthRank.setSelected(true);
                break;
            case 2:
                rankBinding.txtViewTotalRank.setSelected(true);
                break;
            default:
                break;
        }
    }
}
