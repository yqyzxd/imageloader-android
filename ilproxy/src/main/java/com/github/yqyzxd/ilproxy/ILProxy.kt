package com.github.yqyzxd.ilproxy

import android.content.Context
import java.io.File
import java.io.InputStream

class ILProxy private constructor(context: Context):ILoader {
    private var  mLoader: ILoader = GlideLoader(context)

    companion object{
        @JvmStatic
        fun with(context: Context): ILProxy {
            return ILProxy(context)
        }
    }

    override fun load(url: String): IImageLoader {
        return mLoader.load(url)
    }

    override fun load(resourceId: Int): IImageLoader {
        return mLoader.load(resourceId)
    }

    override fun load(file: File): IImageLoader {
        return mLoader.load(file)
    }

    override fun load(ips: InputStream): IImageLoader {
        return mLoader.load(ips)
    }


    override fun placeholder(resuoureId: Int): ILoader {
        mLoader.placeholder(resuoureId)
        return this
    }

    override fun apply(decorator: IDecorator): ILoader {
        mLoader.apply(decorator)
        return this
    }
}