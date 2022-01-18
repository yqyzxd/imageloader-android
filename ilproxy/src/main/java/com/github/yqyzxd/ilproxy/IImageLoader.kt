package com.github.yqyzxd.ilproxy

import android.graphics.drawable.Drawable
import android.widget.ImageView

/**
 * Copyright (C), 2015-2022, 杭州迈优文化创意有限公司
 * FileName: IImageLoader
 * Author: wind
 * Date: 2022/1/18 4:42 下午
 * Description: 描述该类的作用
 * Path: 路径
 * History:
 *  <author> <time> <version> <desc>
 *  作者姓名  修改时间   版本号     描述
 *
 */
interface IImageLoader {
    fun into(iv:ImageView)
    fun <T> into(listener: ILoadListener<T>)
}

interface ILoadListener<T> {
    fun onLoaded(resource: T)
    fun onLoadError(e: Exception)
}