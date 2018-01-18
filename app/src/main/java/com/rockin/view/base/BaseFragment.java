package com.rockin.view.base;

import android.support.v4.app.Fragment;

import com.rockin.utils.ToastUtil;

/**
 * @author Pender
 * @date 2017/11/16
 */
public class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 避免 Toast 持有 Context 的引用造成内存泄漏
        ToastUtil.cancelToast();
    }
}
