package com.yj.banner.xxxbanner.util

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Rect
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.Display
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.Date

/**
 * Created by zmy on 2015/7/8.
 *
 * @author zmy
 */
object SystemUtil {
    /** tag  */
    val TAG = SystemUtil::class.java.simpleName

    /**
     * 获取View所在窗口状态栏的高度（像素值）
     */
    fun getStatusBarHeight(view: View?): Int {
        if (view == null) {
            return 0
        }

        val rect = Rect()
        view.getWindowVisibleDisplayFrame(rect)

        return rect.top
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    fun getVersion(context: Context): String? {
        try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)

            return info.versionName
        } catch (e: Exception) {
            return null
        }

    }

    /**
     * 获取手机屏幕宽度
     *
     * @return 屏幕宽度, in pixel
     */
    fun getScreenWidth(context: Context): Int {
        val dm = DisplayMetrics()
        val mDisplay = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
                .defaultDisplay
        mDisplay.getMetrics(dm)
        return dm.widthPixels
    }

    /**
     * 获取手机屏幕高度
     *
     * @return 屏幕高度, in pixel
     */
    fun getScreenHeight(context: Context): Int {
        val dm = DisplayMetrics()
        val mDisplay = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
                .defaultDisplay
        mDisplay.getMetrics(dm)
        return dm.heightPixels
    }

    fun getScrreenWidthPixels(context: Context?): Int {
        return context?.resources?.displayMetrics?.widthPixels ?: 0

    }

    fun getScrreenHeightPixels(context: Context?): Int {
        return context?.resources?.displayMetrics?.heightPixels ?: 0
    }

    /**
     * @param context
     * must be Activity context.
     */
    @Deprecated("")
    fun dp2px(context: Context, dpValue: Int): Int {
        val metric = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(metric)

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), metric).toInt()
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
                context.resources.displayMetrics).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }





}
