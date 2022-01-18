package com.github.yqyzxd.ilproxy

import java.io.File
import java.io.InputStream

/**
 * Copyright (C), 2015-2022, 杭州迈优文化创意有限公司
 * FileName: ILoad
 * Author: wind
 * Date: 2022/1/18 5:14 下午
 * Description: 描述该类的作用
 * Path: 路径
 * History:
 *  <author> <time> <version> <desc>
 *  作者姓名  修改时间   版本号     描述
 *
 */
interface ILoader {
    fun load(url:String):IImageLoader
    fun load(resourceId:Int):IImageLoader
    fun load(file:File):IImageLoader
    fun load(ips:InputStream):IImageLoader


    fun placeholder(resuoureId:Int):ILoader
    fun apply(decorator:IDecorator):ILoader
}