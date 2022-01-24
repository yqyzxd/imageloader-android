package com.wind.wrapper.imageloader;

import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

public abstract class SimpleImageLoader implements IImageLoader {
    public abstract SimpleImageLoader placeholder(int placeholderDrawable);
    public abstract SimpleImageLoader blur(int radius, int sampling);
    public abstract SimpleImageLoader asGif(int repeatCount, Animatable2Compat.AnimationCallback callback);
    public abstract SimpleImageLoader asBitmap();
}
