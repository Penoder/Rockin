package com.rockin.view.homepage.itemView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.rockin.R;
import com.rockin.databinding.ItemRecommendVideoBinding;
import com.rockin.entity.table.Video;
import com.rockin.utils.TimeUtil;

/**
 * @author Penoder
 * @date 2018/1/10.
 */

public class ItemVideoViewModel {

    private Video mVideo;

    private Context mContext;

    /**
     * 种类 加 时间 （#开胃 / 01'32''）
     */
    public ObservableField<String> categoryAndTime = new ObservableField<>();

    public ObservableField<String> videoTitle = new ObservableField<>();

    public ObservableField<String> feedUrl = new ObservableField<>();

    public ItemVideoViewModel() {

    }

    public ItemVideoViewModel(Video mVideo, Context mContext) {
        if (mVideo == null) {
            return;
        }
        this.mVideo = mVideo;
        this.mContext = mContext;
        categoryAndTime.set(mVideo.category + " / " + TimeUtil.secondToTime(mVideo.duration));
        videoTitle.set(mVideo.title);
        feedUrl.set(mVideo.feed);

//        ItemRecommendVideoBinding recommendBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_recommend_video, null, false);
//        Glide.with(mContext).load(mVideo.feed).into(recommendBinding.imgViewRecommendVideoFeed);
    }

}
