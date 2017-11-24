package com.rockin.view.inform;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rockin.R;
import com.rockin.rockin.databinding.FragmentInformBinding;
import com.rockin.view.base.BaseFragment;

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
        initView(informBinding.getRoot());
        informBinding.setViewModel(this);
        informBinding.executePendingBindings();
        return informBinding.getRoot();
    }

    private void initView(View root) {

    }
}
