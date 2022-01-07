package com.wind.wrapper.imageloader;

public abstract class SimpleImageLoader implements IImageLoader {
    abstract IImageLoader placeholder(int placeholderDrawable);
}
