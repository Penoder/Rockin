package com.penoder.mylibrary.mvvm.bindingadapter.refresh;

import android.databinding.BindingAdapter;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.penoder.mylibrary.refresh.PenoderRefreshLayout;
import com.penoder.mylibrary.refresh.api.RefreshLayout;
import com.penoder.mylibrary.refresh.listener.OnLoadmoreListener;
import com.penoder.mylibrary.refresh.listener.OnRefreshListener;

/**
 * @author Penoder
 * @date 18-3-24.
 */
public class ViewBindingAdapter {

    @BindingAdapter({"onRefreshCommand"})
    public static void onRefreshCommand(PenoderRefreshLayout refreshLayout, final ReplyCommand onRefreshCommand) {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (onRefreshCommand != null) {
                    onRefreshCommand.execute();
                }
            }
        });
    }

    @BindingAdapter({"onLoadingCommand"})
    public static void onLoadingCommand(PenoderRefreshLayout refreshLayout, final ReplyCommand onLoadingCommand) {
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (onLoadingCommand != null) {
                    onLoadingCommand.execute();
                }
            }
        });
    }

}
