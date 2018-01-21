package com.rockin.view.found.attention;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rockin.R;
import com.rockin.databinding.FragmentFoundAttentionBinding;
import com.rockin.view.base.BaseFragment;

/**
 * @author Penoder
 * @date 2018/1/21.
 */
public class AttentionFragment extends BaseFragment {

    private Context mContext;

    private FragmentFoundAttentionBinding attentionBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        attentionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_found_attention, container, false);
        attentionBinding.setViewModel(this);
        attentionBinding.executePendingBindings();
        return attentionBinding.getRoot();
    }
}
