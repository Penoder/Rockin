package com.rockin.view.person;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
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
    }

    /**
     * 返回按钮
     */
    public ReplyCommand onBackCommand = new ReplyCommand(this::finish);
}
