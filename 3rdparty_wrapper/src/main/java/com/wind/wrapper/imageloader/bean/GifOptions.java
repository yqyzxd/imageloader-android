package com.wind.wrapper.imageloader.bean;

import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

/**
 * Copyright (C), 2015-2022, 杭州迈优文化创意有限公司
 * FileName: GifOptions
 * Author: wind
 * Date: 2022/1/19 11:33 上午
 * Description: 描述该类的作用
 * Path: 路径
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名  修改时间   版本号     描述
 */
public class GifOptions {
    private int repeatCount;
    private Animatable2Compat.AnimationCallback callback;

    public GifOptions(int repeatCount, Animatable2Compat.AnimationCallback callback) {
        this.repeatCount = repeatCount;
        this.callback = callback;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public Animatable2Compat.AnimationCallback getCallback() {
        return callback;
    }

    public void setCallback(Animatable2Compat.AnimationCallback callback) {
        this.callback = callback;
    }
}
