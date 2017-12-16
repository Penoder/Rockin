package com.rockin.view.base;

import android.support.v4.app.Fragment;

import com.rockin.entity.eventbus.BaseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * @author Pender
 * @date 2017/11/16
 */
public class BaseFragment extends Fragment {


    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    /**
     * 用于 post Event
     *
     * @param event
     */
    @Subscribe
    public void postEvent(BaseEvent event) {
        EventBus.getDefault().post(event);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
