package com.wind.wrapper.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class GlideWrapper extends SimpleImageLoader{

    private Context mContext;
    private int mPlaceholderDrawable;
    private GlideWrapper(Context context){
       this.mContext=context;
    }

    public static GlideWrapper with(Context context){
        return new GlideWrapper(context);
    }

    public GlideWrapper placeholder(int placeholderDrawable){
        this.mPlaceholderDrawable=placeholderDrawable;
        return this;
    }
    @Override
    public void show(String uri, ImageView iv) {
        if (isFinished()){
            return;
        }
        Glide.with(mContext)
                .load(uri)
                .placeholder(mPlaceholderDrawable)
                .into(iv);
    }

    private boolean isFinished(){
        if (mContext instanceof Activity){
            Activity activity= (Activity) mContext;
            return activity.isFinishing();
        }
        return false;
    }

    @Override
    public void show(final String uri, final ILoadListener listener) {
        if (isFinished()){
            return;
        }
        Glide.with(mContext)
                .load(uri)
                .placeholder(mPlaceholderDrawable)
                .into(/*new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        listener.onLoadError(e);
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        listener.onLoadSuccess(uri,resource);
                    }
                }*/new SimpleTarget<Drawable>() {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        listener.onLoadError(new RuntimeException("图片加载失败"));
                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource,
                                                @Nullable Transition<? super Drawable> transition) {
                        listener.onLoadSuccess(uri,resource);
                    }
                });
    }

    @Override
    public void show(int drawableId, ImageView iv) {
        try {
            Glide.with(mContext)
                    .load(drawableId)
                    .placeholder(mPlaceholderDrawable)
                    .into(iv);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
