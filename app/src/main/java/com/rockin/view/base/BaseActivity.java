package com.rockin.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rockin.utils.ToastUtil;

/**
 * @author Penoder
 * @date 2017/11/16
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 避免 Toast 持有 Context 的引用造成内存泄漏
        ToastUtil.cancelToast();
    }
}
