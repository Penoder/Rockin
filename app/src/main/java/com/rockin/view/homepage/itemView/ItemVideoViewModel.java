package com.rockin.view.homepage.itemView;

import android.content.Context;
import android.databinding.ObservableField;

import com.rockin.entity.homepage.HomeEntity;
import com.penoder.mylibrary.utils.TimeUtil;

/**
 * @author Penoder
 * @date 2018/1/10.
 */
public class ItemVideoViewModel {

    public HomeEntity mHomeEntity;

    private Context mContext;

    /**
     * 种类 加 时间 （#开胃 / 01'32''）
     */
    public ObservableField<String> categoryAndTime = new ObservableField<>();

    public ObservableField<String> videoTitle = new ObservableField<>();

    public ObservableField<String> feedUrl = new ObservableField<>();

    public ItemVideoViewModel() {

    }

    public ItemVideoViewModel(HomeEntity homeEntity, Context mContext) {
        if (homeEntity == null || homeEntity.getVideo() == null) {
            return;
        }
        this.mHomeEntity = homeEntity;
        this.mContext = mContext;
        categoryAndTime.set(homeEntity.getVideo().category + " / " + TimeUtil.secondToTime(homeEntity.getVideo().duration));
        videoTitle.set(homeEntity.getVideo().title);
        feedUrl.set(homeEntity.getVideo().feed);
    }

//    public ReplyCommand<Target<GlideDrawable>> onFailureLoadImgCommand = new ReplyCommand<>((target) -> {
//        LogUtil.i("ItemVideoViewModel --> : 加载图片失败");
//    });

}
