package com.rockin.view.found.classify;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rockin.R;
import com.rockin.databinding.FragmentFoundClassifyBinding;
import com.rockin.view.base.BaseFragment;

/**
 * @author Penoder
 * @date 2018/1/21.
 */
public class ClassifyFragment extends BaseFragment {

    private Context mContext;

    private FragmentFoundClassifyBinding classifyBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        classifyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_found_classify, container, false);
        classifyBinding.setViewModel(this);
        classifyBinding.executePendingBindings();
        return classifyBinding.getRoot();
    }
}
