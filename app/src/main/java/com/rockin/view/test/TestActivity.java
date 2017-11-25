package com.rockin.view.test;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.penoder.mylibrary.okhttp.OkCallBack;
import com.penoder.mylibrary.okhttp.OkHttpManager;
import com.rockin.R;
import com.rockin.config.RocApi;
import com.rockin.databinding.ActivityTestBinding;
import com.rockin.view.base.BaseActivity;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @author Penoder
 * @date 2017/11/25
 */
public class TestActivity extends BaseActivity {

    public ObservableField<String> message = new ObservableField<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestBinding testBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        testBinding.setViewModel(this);
    }

    public ReplyCommand onGetStringCommand = new ReplyCommand(() -> {
        OkHttpManager.create()
                .addUrl(RocApi.TEST_SERVLET + "?tag=1")
                .execute(new OkCallBack<String>() {
                    @Override
                    public void failure(Call call, Exception e) {
                        message.set("GET - String : " + e.getMessage());
                    }

                    @Override
                    public void onResponse(boolean isSuccess, Response response, String obj) {
                        if (isSuccess) {
                            message.set(obj);
                        }
                    }
                }, String.class);
    });

    public ReplyCommand onGetListCommand = new ReplyCommand(() -> {
        OkHttpManager.create()
                .addUrl(RocApi.TEST_SERVLET + "?tag=2")
                .execute(new OkCallBack<List<String>>() {
                    @Override
                    public void failure(Call call, Exception e) {
                        message.set("GET - List : " + e.getMessage());
                    }

                    @Override
                    public void onResponse(boolean isSuccess, Response response, List<String> obj) {
                        if (isSuccess && obj != null && obj.size() > 0) {
                            for (String str : obj) {
                                message.set(str + "\n" + message.get());
                            }
                        }
                    }
                }, List.class);
    });

    public ReplyCommand onPostStringCommand = new ReplyCommand(() -> {
        OkHttpManager.create()
                .addUrl(RocApi.TEST_SERVLET)
                .post()
                .addParam("tag", "1")
                .execute(new OkCallBack<String>() {
                    @Override
                    public void failure(Call call, Exception e) {
                        message.set("POST - String : " + e.getMessage());
                    }

                    @Override
                    public void onResponse(boolean isSuccess, Response response, String obj) {
                        if (isSuccess) {
                            message.set(obj);
                        }
                    }
                }, String.class);
    });

    public ReplyCommand onPostListCommand = new ReplyCommand(() -> {
        OkHttpManager.create()
                .addUrl(RocApi.TEST_SERVLET)
                .post()
                .addParam("tag", "2")
                .execute(new OkCallBack<List<String>>() {
                    @Override
                    public void failure(Call call, Exception e) {
                        message.set("POST - List : " + e.getMessage());
                    }

                    @Override
                    public void onResponse(boolean isSuccess, Response response, List<String> obj) {
                        if (isSuccess && obj != null && obj.size() > 0) {
                            for (String str : obj) {
                                message.set(str + "\n" + message.get());
                            }
                        }
                    }
                }, List.class);
    });

    public ReplyCommand onFailureCommand = new ReplyCommand(() -> {

    });

}
