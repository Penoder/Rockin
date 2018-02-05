package com.rockin.view.found.hot;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rockin.R;
import com.rockin.databinding.ActivityHotBannerBinding;
import com.rockin.view.base.BaseActivity;

/**
 * @author wangpeng
 * @date 18-2-5.
 */

public class HotBannerActivity extends BaseActivity {

    private ActivityHotBannerBinding bannerBinding;

    public ObservableField<String> actionUrl = new ObservableField<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bannerBinding = DataBindingUtil.setContentView(this, R.layout.activity_hot_banner);
    }
}
