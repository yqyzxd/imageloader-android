package com.wind.wrapper.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;


public class GlideWrapper extends SimpleImageLoader {

    private Context mContext;
    private boolean mBlur = false;
    private int mBlurRadius = 20;
    private int mBlurSampling = 20;
    private int mPlaceholderDrawable;
    private boolean mAsGif=false;
    private GlideWrapper(Context context) {
        this.mContext = context;
    }

    public static GlideWrapper with(Context context) {
        return new GlideWrapper(context);
    }

    public GlideWrapper placeholder(int placeholderDrawable) {
        this.mPlaceholderDrawable = placeholderDrawable;
        return this;
    }

    public GlideWrapper blur(boolean blur, int radius, int sampling) {
        this.mBlur = blur;
        this.mBlurRadius = radius;
        this.mBlurSampling = sampling;
        return this;
    }

    @Override
    IImageLoader asGif() {
        mAsGif=true;
        return this;
    }

    @Override
    public void show(String uri, ImageView iv) {
        if (isFinished()) {
            return;
        }

        if (mAsGif){
            Glide.with(mContext).asGif()
                    .load(uri)
                    .placeholder(mPlaceholderDrawable)
                    .into(iv);
        }else {
            RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                    .load(uri)
                    .placeholder(mPlaceholderDrawable);

            if (mBlur) {
                requestBuilder.apply(RequestOptions.bitmapTransform(new BlurTransformation(mBlurRadius, mBlurSampling)));
            }
            requestBuilder.into(iv);
        }




    }

    private boolean isFinished() {
        if (mContext instanceof Activity) {
            Activity activity = (Activity) mContext;
            return activity.isFinishing();
        }
        return false;
    }

    @Override
    public void show(final String uri, final ILoadListener listener) {
        if (isFinished()) {
            return;
        }
        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(uri)
                .placeholder(mPlaceholderDrawable);
        if (mBlur) {
            requestBuilder.apply(RequestOptions.bitmapTransform(new BlurTransformation(mBlurRadius, mBlurSampling)));
        }

        requestBuilder.into(new SimpleTarget<Drawable>() {
            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                listener.onLoadError(new RuntimeException("图片加载失败"));
            }

            @Override
            public void onResourceReady(@NonNull Drawable resource,
                                        @Nullable Transition<? super Drawable> transition) {
                listener.onLoadSuccess(uri, resource);
            }
        });
    }

    @Override
    public void show(int drawableId, ImageView iv) {
        try {

            if (mAsGif){
                Glide.with(mContext).asGif()
                        .load(drawableId)
                        .placeholder(mPlaceholderDrawable)
                        .into(iv);
            }else {
                RequestBuilder<Drawable> requestBuilder =  Glide.with(mContext)
                        .load(drawableId)
                        .placeholder(mPlaceholderDrawable);
                if (mBlur) {
                    requestBuilder.apply(RequestOptions.bitmapTransform(new BlurTransformation(mBlurRadius, mBlurSampling)));
                }
                requestBuilder.into(iv);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
