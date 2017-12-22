package com.rockin.view.splash;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rockin.MainActivity;
import com.rockin.R;
import com.rockin.databinding.ActivitySplashBinding;
import com.rockin.utils.SPUtils;
import com.rockin.view.base.BaseActivity;

/**
 * 启动页，如果用户是第一次进入 App，直接跳转到 引导页，
 * 否则执行背景图的缩放动画，然后跳转到 MainActivity
 *
 * @author Penoder
 * @date 2017-11-17
 */
public class SplashActivity extends BaseActivity {

    ActivitySplashBinding splashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        ifFirstIn();

    }

    /**
     * 判断当前是不是第一次进入App
     */
    private void ifFirstIn() {
        // 存在记录表示非第一进入 App
        if (SPUtils.contains(this, "FIRST_SPLASH")) {
            startAnimation();
        } else {    // 第一次进入 App
            SPUtils.put(this, "FIRST_SPLASH", false);
            startActivity(new Intent(this, GuideActivity.class));
            finish();
        }
    }

    /**
     * 执行背景图的缩放，然后跳转到主界面
     */
    private void startAnimation() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.translate_from_right_to_left, 0);
            new Handler().postDelayed(this::finish, 500);
        }, 2000);
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_from_center_to_out);
        scaleAnimation.setFillAfter(true);
        splashBinding.imgViewSplash.startAnimation(scaleAnimation);
    }
}
