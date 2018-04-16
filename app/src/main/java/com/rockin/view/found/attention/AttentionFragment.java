package com.rockin.view.found.attention;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.rockin.R;
import com.rockin.config.EyeApi;
import com.rockin.databinding.FragmentFoundAttentionBinding;
import com.rockin.view.base.BaseFragment;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 关注模块
 *
 * @author Penoder
 * @date 2018/1/21.
 */
public class AttentionFragment extends BaseFragment {

    private Context mContext;

    private FragmentFoundAttentionBinding attentionBinding;

    private MyHandler mHandler;

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

        mHandler = new MyHandler(this);
        getFollowData();

        return attentionBinding.getRoot();
    }

    private void getFollowData() {
        String followUrl = EyeApi.FOLLOW;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(followUrl)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    /**
     * 刷新事件
     */
    public ReplyCommand onRefreshCommand = new ReplyCommand(() -> {

    });

    /**
     * 加载事件
     */
    public ReplyCommand onLoadingCommand = new ReplyCommand(() -> {

    });

    /**
     * 网络请求状态回调
     */
    private static class MyHandler extends Handler {

        private WeakReference<AttentionFragment> weakReference;

        MyHandler(AttentionFragment fragment) {
            weakReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case -1:
                    break;
                case 0:
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler.weakReference.clear();
            mHandler = null;
        }
    }
}