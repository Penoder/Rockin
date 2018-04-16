package com.penoder.mylibrary.mvvm.bindingadapter.swiperefresh;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;

/**
 * @author kelin
 * @date 16-4-26
 */
public class ViewBindingAdapter {
    @BindingAdapter({"onFlushCommand"})
    public static void onFlushCommand(SwipeRefreshLayout swipeRefreshLayout, final ReplyCommand onFlushCommand) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (onFlushCommand != null) {
                    onFlushCommand.execute();
                }
            }
        });
    }

}
