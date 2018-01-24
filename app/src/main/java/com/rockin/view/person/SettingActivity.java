package com.rockin.view.person;

import android.os.Bundle;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.rockin.R;
import com.rockin.view.base.BaseActivity;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    /**
     * 返回按钮
     */
    public ReplyCommand onBackCommand = new ReplyCommand(this::finish);
}
