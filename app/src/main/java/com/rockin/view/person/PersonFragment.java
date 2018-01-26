package com.rockin.view.person;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.penoder.mylibrary.utils.ToastUtil;
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

    /**
     * 版本信息,应该有API直接获取当前版本信息吧
     */
    public ObservableField<String> version = new ObservableField<>("Version 3.14.254");

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

    /**
     * 我的消息
     */
    public ReplyCommand onMyMsgCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(mContext, "我的消息");
    });

    /**
     * 我的关注
     */
    public ReplyCommand onMyAttentionCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(mContext, "我的关注");
    });

    /**
     * 我的缓存
     */
    public ReplyCommand onMyCacheCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(mContext, "我的缓存");
    });

    /**
     * 观看记录
     */
    public ReplyCommand onWatchRecordCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(mContext, "观看记录");
    });

    /**
     * 意见反馈
     */
    public ReplyCommand onFeedBackCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(mContext, "意见反馈");
    });

    /**
     * 我要投稿
     */
    public ReplyCommand onContributeCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(mContext, "我要投稿");
    });
}
