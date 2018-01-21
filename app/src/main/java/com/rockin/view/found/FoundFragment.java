package com.rockin.view.found;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.rockin.R;
import com.rockin.adapter.CommonFragmentAdapter;
import com.rockin.databinding.FragmentFoundBinding;
import com.rockin.utils.ScreenUtils;
import com.rockin.utils.ToastUtil;
import com.rockin.view.base.BaseFragment;
import com.rockin.view.found.attention.AttentionFragment;
import com.rockin.view.found.classify.ClassifyFragment;
import com.rockin.view.found.hot.HotFragment;
import com.rockin.view.homepage.SearchActivity;

/**
 * 关注 Fragment
 *
 * @author Penoder
 * @date 2017/11/16.
 */
public class FoundFragment extends BaseFragment {

    private Context mContext;
    private FragmentFoundBinding foundBinding;

    private BaseFragment hotFragment, attentionFragment, classifyFragment;

    /**
     * 记录三个标签的居中位置，以及 选中的 指示器的 宽度
     */
    private float hotCenterX, attentionCenterX, classifyCenterX, indicatorHalfWidth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        foundBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_found, container, false);
        initView(foundBinding.getRoot());
        foundBinding.setViewModel(this);
        foundBinding.executePendingBindings();
        return foundBinding.getRoot();
    }

    private void initView(View view) {
        hotFragment = new HotFragment();
        attentionFragment = new AttentionFragment();
        classifyFragment = new ClassifyFragment();
        CommonFragmentAdapter adapter = new CommonFragmentAdapter(getChildFragmentManager(), hotFragment, attentionFragment, classifyFragment);
        foundBinding.viewPagerDiscovery.setAdapter(adapter);
        foundBinding.viewPagerDiscovery.setCurrentItem(0);
        foundBinding.viewPagerDiscovery.setOffscreenPageLimit(2);
        foundBinding.txtViewTabHot.setSelected(true);

        int screenWidth = ScreenUtils.getScreenWidth(mContext);


        hotCenterX = screenWidth / 6;
        attentionCenterX = screenWidth / 2;
        classifyCenterX = screenWidth * 5 / 6;

        /**
         * 屏幕宽度 和 ImgView 宽度的 单位 应该没统一
         */
        foundBinding.imgViewTabSelected.measure(0, 0);
        indicatorHalfWidth = foundBinding.imgViewTabSelected.getMeasuredWidth() * 0.5F;

        foundBinding.imgViewTabSelected.setX(hotCenterX - indicatorHalfWidth);

    }

    /**
     * ViewPager 的 selected 事件
     */
    public ReplyCommand<Integer> onViewPagerSelectedCommand = new ReplyCommand<Integer>((position) -> {
        selectTab(position);
        float localX = 0;
        if (position == 0) {
            localX = hotCenterX - indicatorHalfWidth;
        } else if (position == 1) {
            localX = attentionCenterX - indicatorHalfWidth;
        } else {
            localX = classifyCenterX - indicatorHalfWidth;
        }
        foundBinding.imgViewTabSelected.setX(localX);
    });

    /**
     * 搜索按钮 点击事件
     */
    public ReplyCommand onSearchCommand = new ReplyCommand(() -> {
        startActivity(new Intent(mContext, SearchActivity.class));
        ((Activity) mContext).overridePendingTransition(R.anim.translate_from_up_to_in, 0);
    });

    /**
     * 我的关注 点击事件
     */
    public ReplyCommand onAttentionCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(mContext, "我的关注");
    });

    /**
     * 热门标签
     */
    public ReplyCommand onHotTabCommand = new ReplyCommand(() -> {
        selectTab(0);
        foundBinding.viewPagerDiscovery.setCurrentItem(0);
        foundBinding.imgViewTabSelected.setX(hotCenterX - indicatorHalfWidth);
    });

    /**
     * 关注标签
     */
    public ReplyCommand onAttentionTabCommand = new ReplyCommand(() -> {
        selectTab(1);
        foundBinding.viewPagerDiscovery.setCurrentItem(1);
        foundBinding.imgViewTabSelected.setX(attentionCenterX - indicatorHalfWidth);
    });

    /**
     * 分类标签
     */
    public ReplyCommand onClassifyTabCommand = new ReplyCommand(() -> {
        selectTab(2);
        foundBinding.viewPagerDiscovery.setCurrentItem(2);
        foundBinding.imgViewTabSelected.setX(classifyCenterX - indicatorHalfWidth);
    });

    /**
     * 切换标签
     *
     * @param position
     */
    private void selectTab(int position) {
        foundBinding.txtViewTabHot.setSelected(false);
        foundBinding.txtViewTabAttention.setSelected(false);
        foundBinding.txtViewTabClassify.setSelected(false);
        switch (position) {
            case 0:
                foundBinding.txtViewTabHot.setSelected(true);
                break;
            case 1:
                foundBinding.txtViewTabAttention.setSelected(true);
                break;
            case 2:
                foundBinding.txtViewTabClassify.setSelected(true);
                break;
            default:
                break;
        }
    }
}