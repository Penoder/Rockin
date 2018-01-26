package com.rockin.view.person;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.penoder.mylibrary.utils.ToastUtil;
import com.rockin.R;
import com.rockin.databinding.ActivitySettingBinding;
import com.rockin.view.base.BaseActivity;

public class SettingActivity extends BaseActivity {

    private ActivitySettingBinding settingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        settingBinding.setViewModel(this);
        initSwitch();
    }

    private void initSwitch() {
        // 使用流量时提醒
        View remindView = findViewById(R.id.include_switchRemind);
        TextView txtViewRemindOpen = (TextView) remindView.findViewById(R.id.txtView_settingOpen);
        TextView txtViewRemindClose = (TextView) remindView.findViewById(R.id.txtView_settingClose);
        txtViewRemindOpen.setSelected(true);
        txtViewRemindClose.setSelected(false);
        txtViewRemindOpen.setOnClickListener(v -> {
            v.setSelected(true);
            txtViewRemindClose.setSelected(false);
        });
        txtViewRemindClose.setOnClickListener(v -> {
            v.setSelected(true);
            txtViewRemindOpen.setSelected(false);
        });

        // 日报更新提醒
        View updateView = findViewById(R.id.include_switchUpdate);
        TextView txtViewUpdateOpen = (TextView) updateView.findViewById(R.id.txtView_settingOpen);
        TextView txtViewUpdateClose = (TextView) updateView.findViewById(R.id.txtView_settingClose);
        txtViewUpdateOpen.setSelected(true);
        txtViewUpdateClose.setSelected(false);
        txtViewUpdateOpen.setOnClickListener(v -> {
            v.setSelected(true);
            txtViewUpdateClose.setSelected(false);
        });
        txtViewUpdateClose.setOnClickListener(v -> {
            v.setSelected(true);
            txtViewUpdateOpen.setSelected(false);
        });

        // wifi 自动播放
        View autoPlayView = findViewById(R.id.include_switchAutoPlay);
        TextView txtViewAutoPlayOpen = (TextView) autoPlayView.findViewById(R.id.txtView_settingOpen);
        TextView txtViewAutoPlayClose = (TextView) autoPlayView.findViewById(R.id.txtView_settingClose);
        txtViewAutoPlayOpen.setSelected(true);
        txtViewAutoPlayClose.setSelected(false);
        txtViewAutoPlayOpen.setOnClickListener(v -> {
            v.setSelected(true);
            txtViewAutoPlayClose.setSelected(false);
        });
        txtViewAutoPlayClose.setOnClickListener(v -> {
            v.setSelected(true);
            txtViewAutoPlayOpen.setSelected(false);
        });

        // 日报更新提醒
        View translationView = findViewById(R.id.include_switchTranslation);
        TextView txtViewTranslationOpen = (TextView) translationView.findViewById(R.id.txtView_settingOpen);
        TextView txtViewTranslationClose = (TextView) translationView.findViewById(R.id.txtView_settingClose);
        txtViewTranslationOpen.setSelected(true);
        txtViewTranslationClose.setSelected(false);
        txtViewTranslationOpen.setOnClickListener(v -> {
            v.setSelected(true);
            txtViewTranslationClose.setSelected(false);
        });
        txtViewTranslationClose.setOnClickListener(v -> {
            v.setSelected(true);
            txtViewTranslationOpen.setSelected(false);
        });
    }

    /**
     * 返回按钮
     */
    public ReplyCommand onBackCommand = new ReplyCommand(this::finish);

    /**
     * 清除缓存
     */
    public ReplyCommand onCleanCacheCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(this, "清除缓存");
    });

    /**
     * 选择缓存路径
     */
    public ReplyCommand onChoiceCachePathCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(this, "选择缓存路径");
    });

    /**
     * 检查版本更新
     */
    public ReplyCommand onCheckVersionCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(this, "检查版本更新");
    });

    /**
     * 用户协议
     */
    public ReplyCommand onUserProtocolCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(this, "用户协议");
    });

    /**
     * 功能声明
     */
    public ReplyCommand onFuncDeclarationCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(this, "功能声明");
    });

    /**
     * 版权举报
     */
    public ReplyCommand onCopyrightCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(this, "版权举报");
    });
}
