package com.github.yqyzxd.imageloader

import android.graphics.drawable.Animatable2
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.wind.wrapper.imageloader.ILProxy

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iv=findViewById<ImageView>(R.id.iv)
        val url="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnimg.ws.126.net%2F%3Furl%3Dhttp%253A%252F%252Fdingyue.ws.126.net%252F2021%252F1006%252F2f89f3adj00r0is70000ic000go009em.jpg%26thumbnail%3D650x2147483647%26quality%3D80%26type%3Djpg&refer=http%3A%2F%2Fnimg.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645156191&t=2af60e96fb4ee6198aad0bcd296e1999"
        val gifUrl="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019ec95a220b2aa80120ba3898bb30.gif&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645163909&t=ede9938e3a13993bdcf3205c96068015"
        ILProxy
            .with(this)
            .placeholder(R.drawable.ic_launcher_background)
            .blur(10,10)
            .asGif(1,object :Animatable2Compat.AnimationCallback(){
                override fun onAnimationEnd(drawable: Drawable?) {
                    super.onAnimationEnd(drawable)
                    println("GIF onAnimationEnd")
                }
        }).show(gifUrl,iv)
    }
}