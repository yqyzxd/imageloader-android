package com.wind.wrapper.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public interface IImageLoader {


    void show(String uri, ImageView iv);
    void show(String uri, ILoadListener listener);
    void show(int drawableId, ImageView iv);


    public interface ILoadListener {
        void onLoadSuccess(String uri, Drawable drawable);

        void onLoadError(Exception e);
    }

}
