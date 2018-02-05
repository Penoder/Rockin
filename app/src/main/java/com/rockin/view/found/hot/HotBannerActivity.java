package com.rockin.view.found.hot;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.rockin.R;
import com.rockin.databinding.ActivityHotBannerBinding;
import com.rockin.view.base.BaseActivity;

/**
 * @author Penoder
 * @date 18-2-5.
 */

public class HotBannerActivity extends BaseActivity {

    private ActivityHotBannerBinding bannerBinding;

    /**
     * 标题
     */
    public ObservableField<String> actionTitle = new ObservableField<>();

    /**
     * WebUrl
     */
    public ObservableField<String> actionUrl = new ObservableField<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bannerBinding = DataBindingUtil.setContentView(this, R.layout.activity_hot_banner);
        bannerBinding.setViewModel(this);
        actionTitle.set(getIntent().getStringExtra("ACTION_TITLE"));
        actionUrl.set(getIntent().getStringExtra("ACTION_URL"));
        bannerBinding.webViewHotBanner.loadUrl(actionUrl.get());
    }

    public ReplyCommand onBackCommand = new ReplyCommand(this::finish);
}
