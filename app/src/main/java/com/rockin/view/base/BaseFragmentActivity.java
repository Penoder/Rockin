package com.rockin.view.base;

import android.support.v4.app.FragmentActivity;

import com.penoder.mylibrary.utils.ToastUtil;

/**
 * @author Penoder
 * @date 2017/11/16
 */
public class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 避免 Toast 持有 Context 的引用造成内存泄漏
        ToastUtil.cancelToast();
    }
}
