package com.penoder.mylibrary.mvvm.bindingadapter.image;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.penoder.mylibrary.mvvm.command.ReplyCommand;

/**
 * @author kelin
 * @date 16-3-24
 */
public final class ViewBindingAdapter {

    @BindingAdapter({"uri"})
    public static void setImageUri(ImageView mImageView, String uri) {
        if (!TextUtils.isEmpty(uri)) {
            mImageView.setImageURI(Uri.parse(uri));

        }
    }

    @BindingAdapter(value = {"uri", "placeholderImageRes", "request_width", "request_height", "onSuccessCommand", "onFailureCommand"}, requireAll = false)
    public static void loadImage(final ImageView imageView, String uri,
                                 @DrawableRes int placeholderImageRes,
                                 int width, int height,
                                 final ReplyCommand<GlideDrawable> onSuccessCommand,
                                 final ReplyCommand<Target<GlideDrawable>> onFailureCommand) {
        imageView.setImageResource(placeholderImageRes);
        if (imageView.getTag() == null && !uri.equals(imageView.getTag())) {
            if (!TextUtils.isEmpty(uri)) {

//                Glide.with(imageView.getContext()).load(uri).placeholder(placeholderImageRes).into(imageView);

                RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        if (onFailureCommand != null) {
                            onFailureCommand.execute(target);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (onSuccessCommand != null) {
                            onSuccessCommand.execute(resource);
                        }
                        return false;
                    }
                };

                if (width > 0 && height > 0) {
                    Glide.with(imageView.getContext())
                            .load(uri)
                            .override(width, height)
                            .placeholder(placeholderImageRes)
                            .listener(requestListener)
                            .into(imageView);
                } else {
                    Glide.with(imageView.getContext())
                            .load(uri)
                            .placeholder(placeholderImageRes)
                            .listener(requestListener)
                            .into(imageView);
                }
                imageView.setTag(uri);
            }
        }
    }
}

