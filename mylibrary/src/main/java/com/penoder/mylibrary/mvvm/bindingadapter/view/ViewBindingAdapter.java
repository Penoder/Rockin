package com.penoder.mylibrary.mvvm.bindingadapter.view;

import android.databinding.BindingAdapter;
import android.support.annotation.IntDef;
import android.view.MotionEvent;
import android.view.View;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.penoder.mylibrary.mvvm.command.ResponseCommand;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * @author kelin
 * @date 16-3-24
 */
public final class ViewBindingAdapter {

    @BindingAdapter({"clickCommand"})
    public static void clickCommand(View view, final ReplyCommand clickCommand) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickCommand != null) {
                    clickCommand.execute();
                }
            }
        });
    }

    @BindingAdapter({"longClickCommand"})
    public static void longClickCommand(View view, final ReplyCommand longClickCommand) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickCommand != null) {
                    longClickCommand.execute();
                }
                return true;
            }
        });
    }

    @BindingAdapter({"requestFocus"})
    public static void requestFocusCommand(View view, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        } else {
            view.clearFocus();
        }
    }

    @BindingAdapter({"onFocusChangeCommand"})
    public static void onFocusChangeCommand(View view, final ReplyCommand<Boolean> onFocusChangeCommand) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (onFocusChangeCommand != null) {
                    onFocusChangeCommand.execute(hasFocus);
                }
            }
        });
    }

    @BindingAdapter({"onTouchCommand"})
    public static void onTouchCommand(View view, final ResponseCommand<MotionEvent, Boolean> onTouchCommand) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (onTouchCommand != null) {
                    return onTouchCommand.execute(event);
                }
                return false;
            }
        });
    }

    @BindingAdapter({"isVisible"})
    public static void isVisible(View view, int visible) {
        if (visible == -1) {
            view.setVisibility(GONE);
        } else if (visible == 0) {
            view.setVisibility(INVISIBLE);
        } else {
            view.setVisibility(VISIBLE);
        }
    }
}

