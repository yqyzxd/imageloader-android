package com.github.yqyzxd.ilproxy

import android.content.Context
import java.io.File
import java.io.InputStream

class GlideLoader(val mContext:Context ):ILoader {

    private var mPlaceholder:Int=0
    private var mDecorators= mutableListOf<IDecorator>()
    override fun load(url: String): IImageLoader {
        TODO("Not yet implemented")
    }

    override fun load(resourceId: Int): IImageLoader {
        TODO("Not yet implemented")
    }

    override fun load(file: File): IImageLoader {
        TODO("Not yet implemented")
    }

    override fun load(ips: InputStream): IImageLoader {
        TODO("Not yet implemented")
    }


    override fun placeholder(resuoureId: Int): ILoader {
        mPlaceholder=resuoureId
        return this
    }

    override fun apply(decorator: IDecorator): ILoader {
        mDecorators.add(decorator)
        return this
    }
}