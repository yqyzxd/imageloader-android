package com.wind.wrapper.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.wind.wrapper.imageloader.bean.BlurOptions;
import com.wind.wrapper.imageloader.bean.GifOptions;


public class GlideWrapper extends SimpleImageLoader {

    private Context mContext;
    private BlurOptions mBlurOption;
    private GifOptions mGifOptions;
    private int mPlaceholderDrawable;
    private boolean mAsBitmap;
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

    public GlideWrapper blur(int radius, int sampling) {
        mBlurOption=new BlurOptions(radius,sampling);
        return this;
    }

    @Override
    public GlideWrapper asGif(int repeatCount, Animatable2Compat.AnimationCallback callback) {
        mGifOptions=new GifOptions(repeatCount,callback);
        return this;
    }

    @Override
    public SimpleImageLoader asBitmap() {
        mAsBitmap=true;
        return this;
    }

    private RequestListener<GifDrawable> mGifRequestListener=new RequestListener<GifDrawable>() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
            return false;
        }

        @Override
        public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
            resource.setLoopCount(mGifOptions.getRepeatCount());
            resource.registerAnimationCallback(mGifOptions.getCallback());
            return false;
        }
    };
    @Override
    public void show(String uri, ImageView iv) {
        apply(uri,iv);
    }

    @Override
    public void show(int drawableId, ImageView iv) {
        apply(drawableId,iv);
    }

    @Override
    public void show(final String uri, final ILoadListener listener) {
        if (isFinished()) {
            return;
        }
        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(uri)
                .placeholder(mPlaceholderDrawable);
        if (mBlurOption!=null) {
            requestBuilder.apply(RequestOptions.bitmapTransform(new BlurTransformation(mBlurOption.getRadius(), mBlurOption.getSampling())));
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

    public void apply(Object source,ImageView iv){
        try {
            if (isFinished()) {
                return;
            }

            RequestBuilder requestBuilder;

            if (mGifOptions!=null){
                requestBuilder= Glide.with(mContext).asGif().listener(mGifRequestListener)
                        .load(source)
                        .placeholder(mPlaceholderDrawable)
                        .error(mPlaceholderDrawable);
            }else {
                if (mAsBitmap){
                    requestBuilder = Glide.with(mContext).asBitmap()
                            .load(source)
                            .placeholder(mPlaceholderDrawable)
                            .error(mPlaceholderDrawable);
                }else {
                    requestBuilder = Glide.with(mContext)
                            .load(source)
                            .placeholder(mPlaceholderDrawable)
                            .error(mPlaceholderDrawable);
                }

            }
            if (mBlurOption!=null) {
                requestBuilder.apply(RequestOptions.bitmapTransform(new BlurTransformation(mBlurOption.getRadius(), mBlurOption.getSampling())));
            }
            requestBuilder.into(iv);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isFinished() {
        if (mContext instanceof Activity) {
            Activity activity = (Activity) mContext;
            return activity.isFinishing();
        }
        return false;
    }
}
