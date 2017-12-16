package com.rockin.view.concern;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rockin.R;
import com.rockin.databinding.FragmentConcernBinding;
import com.rockin.view.base.BaseFragment;

/**
 * 关注 Fragment
 *
 * @author Penoder
 * @date 2017/11/16.
 */
public class ConcernFragment extends BaseFragment {

    private Context mContext;
    private FragmentConcernBinding concernBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        concernBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_concern, container, false);
        initView(concernBinding.getRoot());
        concernBinding.setViewModel(this);
        concernBinding.executePendingBindings();
        return concernBinding.getRoot();
    }

    private void initView(View root) {

    }
}