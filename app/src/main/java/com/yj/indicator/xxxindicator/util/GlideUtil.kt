package com.yj.banner.xxxbanner.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.util.Log
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.yj.indicator.xxxindicator.util.GlideRoundTransform

/**
 * desc: Glide图片加载工具类
 * time: 2017/11/28 0028
 *
 * @author yinjin
 */
object GlideUtil {

    /** tag  */
    private val TAG = GlideUtil::class.java.simpleName

    /**
     * 显示图片
     *
     * @param imageView
     * img
     * @param url
     * 图片地址
     * @param defaultDrawableRes
     * 默认图片
     */
    fun loadPicture(imageView: ImageView, url: String, @DrawableRes defaultDrawableRes: Int) {

        val options = RequestOptions()
                .centerCrop()
                .placeholder(defaultDrawableRes)
                .error(defaultDrawableRes)
                .priority(Priority.HIGH)
                //.skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
        try {
            if (!url.isNullOrBlank()) {
                Glide.with(imageView.context)
                        .load(url)
                        .apply(options)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                                return false
                            }

                            override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                if (resource is GifDrawable) {
                                    resource.setLoopCount(1)
                                    resource.stop()
                                }
                                return false
                            }
                        })
                        .into(imageView)

            } else {
                imageView.setImageResource(defaultDrawableRes)
            }
        } catch (e: Exception) {
            Log.e(TAG, "glide图片加载失败", e)
            imageView.setImageResource(defaultDrawableRes)
        }


    }

    /**
     * 显示图片
     *
     * @param imageView
     * img
     * @param url
     * 图片地址
     */
    fun loadRoundPicture(imageView: ImageView, url: String, radius: Float, @DrawableRes defaultDrawableRes: Int) {

        try {

            val options = RequestOptions()
                    .placeholder(defaultDrawableRes)
                    .error(defaultDrawableRes)
                    .priority(Priority.HIGH)
                    .transform(GlideRoundTransform(radius))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
            if (!url.isNullOrBlank()) {
                Glide.with(imageView.context)
                        .load(url)
                        .apply(options)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                                return false
                            }

                            override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                if (resource is GifDrawable) {
                                    resource.setLoopCount(1)
                                    resource.stop()
                                }
                                return false
                            }
                        })
                        .into(imageView)

            }
        } catch (e: Exception) {
            Log.e(TAG, "glide图片加载失败", e)
        }


    }

    /**
     * 显示图片
     *
     * @param imageView
     * img
     * @param url
     * 图片地址
     */
    fun loadRoundPicture(imageView: ImageView, url: String, radius: Float
    ) {


        try {
            val options = RequestOptions()
                    .priority(Priority.HIGH)
                    .transform(GlideRoundTransform(radius))
                    //.skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
            if (!url.isNullOrBlank()) {
                Glide.with(imageView.context)
                        .load(url)
                        .apply(options)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                                return false
                            }

                            override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                if (resource is GifDrawable) {
                                    resource.setLoopCount(1)
                                    resource.stop()
                                }
                                return false
                            }
                        })
                        .into(imageView)

            }
        } catch (e: Exception) {
            Log.e(TAG, "glide图片加载失败", e)
        }


    }

    /**
     * 显示图片
     *
     * @param imageView
     * img
     * @param url
     * 图片地址
     */
    fun loadPicture(imageView: ImageView, url: String) {
        try {
            if (!url.isNullOrBlank()) {
                Glide.with(imageView.context)
                        .load(url)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                                return false
                            }

                            override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                if (resource is GifDrawable) {
                                    resource.setLoopCount(1)
                                    resource.stop()
                                }
                                return false
                            }
                        })
                        .into(imageView)

            }
        } catch (e: Exception) {
            Log.e(TAG, "glide图片加载失败", e)
        }

    }

    /**
     * 这个是以前用Glide加载过图片然后在重新加载的时候，直接用clear直接取消上一次加载
     *
     * @param context
     * 上下文
     * @param imageView
     * imageView
     */
    fun clearLoad(context: Context, imageView: ImageView) {
        try {
            Glide.with(context).clear(imageView)
        } catch (e: Exception) {
            Log.e(TAG, "glide图片clear失败", e)
        }

    }
}
