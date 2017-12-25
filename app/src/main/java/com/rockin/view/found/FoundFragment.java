package com.rockin.view.found;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rockin.R;
import com.rockin.databinding.FragmentFoundBinding;
import com.rockin.view.base.BaseFragment;

/**
 * 关注 Fragment
 *
 * @author Penoder
 * @date 2017/11/16.
 */
public class FoundFragment extends BaseFragment {

    private Context mContext;
    private FragmentFoundBinding foundBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        foundBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_found, container, false);
        initView(foundBinding.getRoot());
        foundBinding.setViewModel(this);
        foundBinding.executePendingBindings();
        return foundBinding.getRoot();
    }

    private void initView(View root) {

    }
}