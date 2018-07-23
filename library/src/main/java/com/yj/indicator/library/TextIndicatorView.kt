package com.yj.indicator.library

import android.content.Context
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

/**
 * desc:普通的指示器
 * time: 2018/7/20
 *
 * @author yinYin
 */
class TextIndicatorView : LinearLayout {
    var viewPager: ViewPager? = null
    private var adapter: PagerAdapter? = null
    private var count: Int = 0
    var orientationStyle = ORIENTATION_DEFAULT
    var indicatorPosition = POSITION_DEFAULT
        set(value) {
            field = value
            gravity = value
            invalidate()
        }
    var mIndicatorSpace = 0F
    var mIndicatorSelectedTextSize: Float = 18F
    var mIndicatorUnselectedTextSize: Float = 12F
    var mIndicatorSelectedColor: Int = Color.parseColor("#FFFFFFFF")
    var mIndicatorUnselectedColor: Int = Color.parseColor("#7A000000")
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
        for (i in 0 until 2) {
            val textView = TextView(context)
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.leftMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mIndicatorSpace, context.resources.displayMetrics).toInt()
            if (i == 0) {
                textView.setTextColor(mIndicatorSelectedColor)
                textView.textSize =mIndicatorSelectedTextSize
                textView.text = ((viewPager?.currentItem?:0)+1).toString()
            } else {
                textView.setTextColor(mIndicatorUnselectedColor)
                textView.textSize =mIndicatorUnselectedTextSize
                textView.text = "/$count"
            }
            addView(textView, params)
        }
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                (getChildAt(0) as TextView).text=(p0+1).toString()
            }
        })
    }

    companion object {
        const val ORIENTATION_DEFAULT = LinearLayout.HORIZONTAL
        const val POSITION_DEFAULT = Gravity.CENTER

    }
}
