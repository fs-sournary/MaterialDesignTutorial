package com.sournary.materialdesigntutorial.extension

import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Created in 23/10/2019 by Sang
 */
fun <T> RequestBuilder<T>.doOnEnd(body: () -> Unit): RequestBuilder<T> =
    addListener(object : RequestListener<T> {
        override fun onLoadFailed(
            e: GlideException?, model: Any?, target: Target<T>?, isFirst: Boolean
        ): Boolean {
            body.invoke()
            return false
        }

        override fun onResourceReady(
            resource: T, model: Any?, target: Target<T>?, dataSource: DataSource?, isFirst: Boolean
        ): Boolean {
            body.invoke()
            return false
        }
    })
