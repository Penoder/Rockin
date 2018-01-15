package com.rockin.view.homepage;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.rockin.R;
import com.rockin.databinding.ActivitySearchBinding;
import com.rockin.view.base.BaseActivity;

public class SearchActivity extends BaseActivity {

    private ActivitySearchBinding searchBinding;

    public ObservableField<String> searchContent = new ObservableField<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        searchBinding.setViewModel(this);
    }

    public ReplyCommand onCancelSearchCommand = new ReplyCommand(this::finish);
}
