package com.wind.wrapper.imageloader.bean;

/**
 * Copyright (C), 2015-2022, 杭州迈优文化创意有限公司
 * FileName: BlurOptions
 * Author: wind
 * Date: 2022/1/19 11:34 上午
 * Description: 描述该类的作用
 * Path: 路径
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名  修改时间   版本号     描述
 */
public class BlurOptions {

    private int radius;
    private int sampling;

    public BlurOptions(int radius, int sampling) {
        this.radius = radius;
        this.sampling = sampling;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getSampling() {
        return sampling;
    }

    public void setSampling(int sampling) {
        this.sampling = sampling;
    }
}
