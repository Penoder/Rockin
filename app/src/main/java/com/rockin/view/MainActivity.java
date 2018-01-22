package com.rockin.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.rockin.R;
import com.rockin.adapter.CommonFragmentAdapter;
import com.rockin.databinding.ActivityMainBinding;
import com.penoder.mylibrary.utils.ToastUtil;
import com.rockin.view.base.BaseFragment;
import com.rockin.view.base.BaseFragmentActivity;
import com.rockin.view.found.FoundFragment;
import com.rockin.view.homepage.HomePageFragment;
import com.rockin.view.inform.InformFragment;
import com.rockin.view.person.PersonFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Penoder
 * @date 2017/11/16
 */
public class MainActivity extends BaseFragmentActivity {

    private ActivityMainBinding mainBinding;

    private BaseFragment homePageFragment, concernFragment, informFragment, personFragment;

    /**
     * 记录上一次选择的标签
     */
    private int lastClickTab = 0;

    private static Boolean isExit = false;

    private Timer tExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setViewModel(this);
        initFragment();
        initListener();
    }

    /**
     * 初始化 主界面的 4个 Fragment
     */
    private void initFragment() {
        homePageFragment = new HomePageFragment();
        concernFragment = new FoundFragment();
        informFragment = new InformFragment();
        personFragment = new PersonFragment();
        mainBinding.viewPagerMain.setAdapter(new CommonFragmentAdapter(getSupportFragmentManager(), homePageFragment, concernFragment, informFragment, personFragment));
        // 设置 ViewPager 中最大缓存三个，所以屏幕中的四个 Fragment 都可以加载出来
        mainBinding.viewPagerMain.setOffscreenPageLimit(3);
        // 设置默认选中的为第一个 Fragment
        mainBinding.viewPagerMain.setCurrentItem(0);
        mainBinding.imgViewTabOne.setSelected(true);
        mainBinding.txtViewTabOne.setSelected(true);
    }

    /**
     * 设置底部导航栏标签的点击事件，即切换标签之间的选中状态
     */
    private void initListener() {
        mainBinding.linearTabOne.setOnClickListener(v -> {
            cancelSelectTab();
            mainBinding.imgViewTabOne.setSelected(true);
            mainBinding.txtViewTabOne.setSelected(true);
            mainBinding.viewPagerMain.setCurrentItem(0);
            compareClickTab();
            lastClickTab = 0;
        });
        mainBinding.linearTabTwo.setOnClickListener(v -> {
            cancelSelectTab();
            mainBinding.imgViewTabTwo.setSelected(true);
            mainBinding.txtViewTabTwo.setSelected(true);
            mainBinding.viewPagerMain.setCurrentItem(1);
            compareClickTab();
            lastClickTab = 1;
        });
        mainBinding.linearTabThree.setOnClickListener(v -> {
            cancelSelectTab();
            mainBinding.imgViewTabThree.setSelected(true);
            mainBinding.txtViewTabThree.setSelected(true);
            mainBinding.viewPagerMain.setCurrentItem(2);
            compareClickTab();
            lastClickTab = 2;
        });
        mainBinding.linearTabFour.setOnClickListener(v -> {
            cancelSelectTab();
            mainBinding.imgViewTabFour.setSelected(true);
            mainBinding.txtViewTabFour.setSelected(true);
            mainBinding.viewPagerMain.setCurrentItem(3);
            compareClickTab();
            lastClickTab = 3;
        });
    }

    private void compareClickTab() {
        if (mainBinding.viewPagerMain.getCurrentItem() == lastClickTab) {
            // 首页视频列表回顶部
            if (mainBinding.viewPagerMain.getCurrentItem() == 0 && homePageFragment != null) {
                ((HomePageFragment) homePageFragment).backToTop();
            }
        }
    }

    /**
     * 用于取消底部导航栏中的 Tab 选择
     */
    private void cancelSelectTab() {
        mainBinding.imgViewTabOne.setSelected(false);
        mainBinding.imgViewTabTwo.setSelected(false);
        mainBinding.imgViewTabThree.setSelected(false);
        mainBinding.imgViewTabFour.setSelected(false);
        mainBinding.txtViewTabOne.setSelected(false);
        mainBinding.txtViewTabTwo.setSelected(false);
        mainBinding.txtViewTabThree.setSelected(false);
        mainBinding.txtViewTabFour.setSelected(false);
    }

    @Override
    public void onBackPressed() {
        doubleClickExit();
    }

    /**
     * 双击退出
     */
    private void doubleClickExit() {
        if (!isExit) {
            isExit = true; // 准备退出
            ToastUtil.showShortToast(this, "再按一次退出");
            if (tExit == null) {
                tExit = new Timer();
            }
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 1500); // 如果1.5秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            overridePendingTransition(0, 0);
            System.exit(0);
        }
    }
}
