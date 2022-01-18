package com.github.yqyzxd.ilproxy

import java.io.BufferedInputStream
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
interface ILoad {
    fun <T> load(source:T):IImageLoader

}