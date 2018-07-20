package com.yj.indicator.library

import android.content.Context
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout

/**
 * desc:普通的指示器
 * time: 2018/7/20
 *
 * @author yinYin
 */
class RectIndicatorView : LinearLayout {
    var viewPager: ViewPager? = null
    private var adapter: PagerAdapter? = null
    private var count: Int = 0
    var orientationStyle = ORIENTATION_DEFAULT
    var indicatorPosition = POSITION_DEFAULT
    var mIndicatorLeftMargin = LEFT_MARGIN_DEFAULT
    var mIndicatorRightMargin = RIGHT_MARGIN_DEFAULT
    var mIndicatorWidth: Float = 5F
    var mIndicatorHeight: Float = 5F
    var mIndicatorSelectedColor: Int = Color.parseColor("#88353535")
    var mIndicatorUnselectedColor: Int = Color.parseColor("#88ffffff")

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, art: AttributeSet) : super(context, art) {
        init()
    }

    constructor(context: Context, art: AttributeSet, defStyleAttr: Int) : super(context, art, defStyleAttr) {
        init()
    }

    private fun init() {
        orientation = orientationStyle
        gravity = indicatorPosition
    }

    fun start() {
        if (viewPager == null) {
            throw RuntimeException("use NormalIndicatorView before ,please setViewPager")
            return
        }
        adapter = viewPager?.adapter
        count = adapter?.count ?: 0
        for (i in 0 until count) {
            val view = View(context)
            val params = LinearLayout.LayoutParams(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mIndicatorWidth, context.resources.displayMetrics).toInt(), TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mIndicatorHeight, context.resources.displayMetrics).toInt())
            params.leftMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mIndicatorLeftMargin, context.resources.displayMetrics).toInt()
            params.rightMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mIndicatorRightMargin, context.resources.displayMetrics).toInt()
            if (i == 0) {
                view.setBackgroundColor(mIndicatorSelectedColor)
            } else {
                view.setBackgroundColor(mIndicatorUnselectedColor)
            }
            addView(view, params)
        }
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
//                Log.e("p0", p0.toString())
//                Log.e("p1", p1.toString())
//                if (p1 != 0f) {
//                    if (p0 == count - 1) {
//                        (getChildAt(p0) as ImageView).scaleX = 1-p1
//                        (getChildAt(p0) as ImageView).scaleY = 1-p1
//                        (getChildAt(0) as ImageView).scaleX = 2 - p1
//                        (getChildAt(0) as ImageView).scaleY = 2 - p1
//                    } else {
//                        (getChildAt(p0) as ImageView).scaleX = 1-p1
//                        (getChildAt(p0) as ImageView).scaleY = 1-p1
//                        (getChildAt(p0 + 1) as ImageView).scaleX = 2 - p1
//                        (getChildAt(p0 + 1) as ImageView).scaleY = 2 - p1
//                    }
//                } else {
//                    (getChildAt(p0) as ImageView).scaleX = 1f
//                    (getChildAt(p0) as ImageView).scaleY = 1f
//                    if (p0 == 0) {
//                        (getChildAt(count-1) as ImageView).scaleX = 1f
//                        (getChildAt(count-1) as ImageView).scaleY = 1f
//                    } else {
//                        (getChildAt(p0-1) as ImageView).scaleX = 1f
//                        (getChildAt(p0-1) as ImageView).scaleY = 1f
//                    }
//
//                }

            }

            override fun onPageSelected(p0: Int) {
                for (i in 0 until count) {
                    if (i == p0) {
                        (getChildAt(i) as View).setBackgroundColor(mIndicatorSelectedColor)
                    } else {
                        (getChildAt(i) as View).setBackgroundColor(mIndicatorUnselectedColor)
                    }
                }

            }
        })
    }

    companion object {
        const val ORIENTATION_DEFAULT = LinearLayout.HORIZONTAL
        const val POSITION_DEFAULT = Gravity.CENTER
        const val LEFT_MARGIN_DEFAULT = 5f
        const val RIGHT_MARGIN_DEFAULT = 5f

    }
}
