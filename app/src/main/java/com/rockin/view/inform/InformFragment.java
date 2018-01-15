package com.rockin.view.inform;

import android.app.Activity;
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
import com.rockin.databinding.FragmentInformBinding;
import com.rockin.view.base.BaseFragment;
import com.rockin.view.homepage.SearchActivity;

/**
 * 通知 Fragment
 *
 * @author Penoder
 * @date 2017/11/16.
 */
public class InformFragment extends BaseFragment {

    private Context mContext;
    private FragmentInformBinding informBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        informBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_inform, container, false);
        informBinding.setViewModel(this);
        informBinding.executePendingBindings();
        return informBinding.getRoot();
    }

    /**
     * 跳转到查询页面
     */
    public ReplyCommand onSearchCommand = new ReplyCommand(() -> {
        startActivity(new Intent(mContext, SearchActivity.class));
        ((Activity)mContext).overridePendingTransition(R.anim.translate_from_up_to_in, 0);
    });

}
