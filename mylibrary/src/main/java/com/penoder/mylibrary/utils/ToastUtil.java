package com.penoder.mylibrary.utils;

import android.content.Context;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by Penoder on 17-12-23.
 */

public class ToastUtil {

    private static Toast mShortToast;

    private static Toast mLongToast;

    public static void showShortToast(Context mContext, String msg) {
        if (mShortToast == null) {
            mShortToast = Toast.makeText(new WeakReference<>(mContext).get(), msg, Toast.LENGTH_SHORT);
        }
        mShortToast.setText(msg);
        mShortToast.show();
    }

    public static void showLongToast(Context mContext, String msg) {
        if (mLongToast == null) {
            mLongToast = Toast.makeText(new WeakReference<>(mContext).get(), msg, Toast.LENGTH_LONG);
        }
        mLongToast.setText(msg);
        mLongToast.show();
    }

    public static void cancelToast() {
        if (mShortToast != null) {
            mShortToast.cancel();
        }
        if (mLongToast != null) {
            mLongToast.cancel();
        }
    }

}
