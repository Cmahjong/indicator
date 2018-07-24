package com.yj.indicator.library

import android.content.Context
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.yj.indicator.library.widget.MagicTextView
import kotlinx.android.synthetic.main.normal_view.view.*
import kotlinx.android.synthetic.main.selected_view.view.*

@Suppress("UNREACHABLE_CODE")
/**
 * desc:普通的指示器
 * time: 2018/7/20
 *
 * @author yinYin
 */
class MagicTextIndicatorView : LinearLayout {
    var viewPager: ViewPager? = null
    private var adapter: PagerAdapter? = null
    private var count: Int = 0
    private val normalView: MagicTextView by lazy {
        LayoutInflater.from(context).inflate(R.layout.normal_view, null).magic_normal_root
    }
    private val selectedView: MagicTextView by lazy {
        LayoutInflater.from(context).inflate(R.layout.selected_view, null).magic_selected_root
    }
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
    var magicTextStyle = MagicTextStyle.INNER
        set(value) {
            field = value
            when (value) {
                MagicTextStyle.INNER -> {
                    normalView.apply {
                        paint.isFakeBoldText = true
                        addInnerShadow(5f, 1f, 0f, Color.parseColor("#80000000"))
                    }
                    selectedView.apply {
                        paint.isFakeBoldText = true
                        addInnerShadow(5f, 1f, 0f, Color.parseColor("#80000000"))
                    }
                }
                MagicTextStyle.OUTER -> {
                    normalView.apply {
                        clearInnerShadows()
                        setStroke(1f, Color.parseColor("#FFFFFFFF"))
                        addOuterShadow(5f, 2f, 1f, Color.parseColor("#80000000"))
                    }
                    selectedView.apply {
                        clearInnerShadows()
                        setStroke(1f, Color.parseColor("#FFFFFFFF"))
                        addOuterShadow(5f, 2f, 1f, Color.parseColor("#80000000"))
                    }
                }
            }
            invalidate()
        }

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
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.leftMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mIndicatorSpace, context.resources.displayMetrics).toInt()
            if (i == 0) {
                selectedView.setTextColor(mIndicatorSelectedColor)
                selectedView.textSize = mIndicatorSelectedTextSize
                selectedView.text = ((viewPager?.currentItem ?: 0) + 1).toString()
                addView(selectedView, params)
            } else {
                normalView.setTextColor(mIndicatorUnselectedColor)
                normalView.textSize = mIndicatorUnselectedTextSize
                normalView.text = "/$count"
                addView(normalView, params)
            }

        }
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                (getChildAt(0) as TextView).text = (p0 + 1).toString()
            }
        })
    }

    companion object {
        const val ORIENTATION_DEFAULT = LinearLayout.HORIZONTAL
        const val POSITION_DEFAULT = Gravity.CENTER

    }
}
