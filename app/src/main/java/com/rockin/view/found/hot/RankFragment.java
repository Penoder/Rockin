package com.rockin.view.found.hot;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rockin.databinding.FragmentRankBinding;
import com.rockin.view.base.BaseFragment;

/**
 * @author Penoder
 * @date 2018/2/21.
 */
public class RankFragment extends BaseFragment {

    private FragmentRankBinding rankBinding;

    /**
     * 接收上个页面传递过来的排行类型，
     * 1. 周排行
     * 2. 月排行
     * 3. 总排行
     */
    private int rankType;

    public ObservableField<String> rankTypeTxt = new ObservableField<>();

    // 定义一个创建对象的接口，让子类决定实例化哪个类——>工厂方法模式
    // 这里的newInstance()是一个静态工厂模式
    public static RankFragment newInstance(int type) {
        RankFragment fragment = new RankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("RANK_TYPE", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            rankType = bundle.getInt("RANK_TYPE");
        }
        switch (rankType) {
            case 1:
                rankTypeTxt.set("111111111");
                break;
            case 2:
                rankTypeTxt.set("222222222");
                break;
            case 3:
                rankTypeTxt.set("333333333");
                break;
            default:
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rankBinding = FragmentRankBinding.inflate(inflater, container, false);
        rankBinding.setViewModel(this);
        rankBinding.executePendingBindings();
        return rankBinding.getRoot();
    }
}
