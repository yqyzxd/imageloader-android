package com.wind.wrapper.imageloader;

import android.animation.ValueAnimator;
import android.content.Context;
import android.widget.ImageView;

import androidx.core.graphics.drawable.IconCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

public class ILProxy extends SimpleImageLoader {
    private IImageLoader loader;
    public ILProxy(Context context){
        loader= GlideWrapper.with(context);
    }

    public static ILProxy with(Context context){
        return new ILProxy(context);
    }

    @Override
    public ILProxy placeholder(int placeholderDrawable){
        if (loader instanceof SimpleImageLoader){
            SimpleImageLoader simpleImageLoader= (SimpleImageLoader) loader;
            simpleImageLoader.placeholder(placeholderDrawable);
        }
        return this;
    }

    @Override
    public ILProxy blur(int radius, int sampling) {
        if (loader instanceof SimpleImageLoader) {
            SimpleImageLoader simpleImageLoader = (SimpleImageLoader) loader;
            simpleImageLoader.blur(radius, sampling);
        }
        return this;
    }
    @Override
    public ILProxy asGif(int repeatCount, Animatable2Compat.AnimationCallback callback) {
        if (loader instanceof SimpleImageLoader) {
            SimpleImageLoader simpleImageLoader = (SimpleImageLoader) loader;
            simpleImageLoader.asGif(repeatCount,callback);
        }
        return this;
    }

    public ILProxy asGif() {
       return asGif(ValueAnimator.INFINITE,null);
    }

    @Override
    public void show(int drawableId, ImageView iv) {
        loader.show(drawableId,iv);
    }
    @Override
    public void show(String uri, ImageView iv) {
        loader.show(uri,iv);
    }

    @Override
    public void show(String uri, ILoadListener listener) {
        loader.show(uri,listener);
    }
}
