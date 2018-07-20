package com.yj.indicator.xxxindicator.util

import android.content.Context
import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import android.graphics.Bitmap




/**
 * desc:
 * time: 2018/3/22
 * @author yinYin
 */
class GlideRoundTransform(radius:Float) : BitmapTransformation() {
    var radius = radius
    override fun updateDiskCacheKey(messageDigest: MessageDigest?) {
    }


    override fun transform(pool: BitmapPool, toTransform: Bitmap,
                           outWidth: Int, outHeight: Int): Bitmap? {
        val bitmap = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight)
        return roundCrop(pool, bitmap)
    }

    private fun roundCrop(pool: BitmapPool, source: Bitmap?): Bitmap? {
        if (source == null) return null

        var result: Bitmap? = pool.get(source.width, source.height,
                Bitmap.Config.ARGB_8888)
        if (result == null) {
            result = Bitmap.createBitmap(source.width, source.height,
                    Bitmap.Config.ARGB_8888)
        }

        val canvas = Canvas(result)
        val paint = Paint()
        paint.shader = BitmapShader(source, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP)
        paint.isAntiAlias = true
        val rectF = RectF(0f, 0f, source.width.toFloat(), source.height.toFloat())
        canvas.drawRoundRect(rectF, radius, radius, paint)
        return result
    }


}