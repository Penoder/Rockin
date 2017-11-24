package com.rockin.view.person;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rockin.R;
import com.rockin.rockin.databinding.FragmentPersonBinding;
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
        initView(personBinding.getRoot());
        personBinding.setViewModel(this);
        personBinding.executePendingBindings();
        return personBinding.getRoot();
    }

    private void initView(View root) {

    }
}
