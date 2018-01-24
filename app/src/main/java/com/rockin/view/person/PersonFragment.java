package com.rockin.view.person;

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
import com.rockin.databinding.FragmentPersonBinding;
import com.rockin.view.base.BaseFragment;

/**
 * 我的 Fragment
 *
 * @author Penoder
 * @date 2017/11/16.
 */
public class PersonFragment extends BaseFragment {

    private Context mContext;
    private FragmentPersonBinding personBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        personBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_person, container, false);
        personBinding.setViewModel(this);
        personBinding.executePendingBindings();
        return personBinding.getRoot();
    }

    /**
     * 设置按钮点击事件，跳转到设置界面
     */
    public ReplyCommand onSettingCommand = new ReplyCommand(() -> {
        startActivity(new Intent(mContext, SettingActivity.class));
    });
}
