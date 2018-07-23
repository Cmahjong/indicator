package com.yj.indicator.library

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL


/**
 * desc:普通的指示器
 * time: 2018/7/20
 *
 * @author yinYin
 */
class CircleMoveIndicatorView : View {
    var viewPager: ViewPager? = null
    private var adapter: PagerAdapter? = null
    private var count: Int = 0
    var orientationStyle = ORIENTATION_DEFAULT
    /** 当前位置默认为0 */
    var currentPosition = 0
        set(value) {
            field = value
            invalidate()
        }
    private val normalPaint by lazy {
        Paint().apply {
            style = Paint.Style.FILL
        }
    }
    private val selectedPaint by lazy {
        Paint().apply {
            style = Paint.Style.FILL
        }
    }
    /** 圓形指示器之间的间隔 */
    var circleSpace: Float = 5F
        set(value) {
            field = value
            invalidate()
        }
    /** 原型指示器大小(半径) */
    var circleRadioSize: Float = 5F
        set(value) {
            field = value
            invalidate()
        }
    /** 普通状态指示器颜色 */
    var normalColor: Int = Color.parseColor("#FFFFFF")
        set(value) {
            field = value
            invalidate()
        }
    /** 选中状态指示器颜色 */
    var selectedColor: Int = Color.parseColor("#353535")
        set(value) {
            field = value
            invalidate()
        }
    /** 是否执行平滑动画 */
    var smoothAnimation: Boolean = false
        set(value) {
            field = value
            invalidate()
        }
    /** 滑动的时候偏移量 */
    private var offset = 0f

    constructor(context: Context, art: AttributeSet) : super(context, art) {
        init(art, 0)
    }

    constructor(context: Context, art: AttributeSet, defStyleAttr: Int) : super(context, art, defStyleAttr) {
        init(art, defStyleAttr)
    }

    private fun init(attrs: AttributeSet, defStyleAttr: Int) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CircleMoveIndicatorView, defStyleAttr, 0)
        normalColor = attributes.getColor(R.styleable.CircleMoveIndicatorView_normalColor, Color.parseColor("#ffe100"))
        selectedColor = attributes.getColor(R.styleable.CircleMoveIndicatorView_selectedColor, Color.parseColor("#00bfff"))
        circleRadioSize = attributes.getDimension(R.styleable.CircleMoveIndicatorView_circleRadioSize, 5f)
        circleSpace = attributes.getDimension(R.styleable.CircleMoveIndicatorView_circleSpace, 5f)
        smoothAnimation = attributes.getBoolean(R.styleable.CircleMoveIndicatorView_smoothAnimation, false)
        normalPaint.color = normalColor
        selectedPaint.color = selectedColor
        attributes.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (orientationStyle == HORIZONTAL) {
            setMeasuredDimension(measureLong(widthMeasureSpec), measureShort(heightMeasureSpec))
        } else {
            setMeasuredDimension(measureShort(widthMeasureSpec), measureLong(heightMeasureSpec))
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        count = adapter?.count ?: 0
        if (count == 0) {
            return
        }
        when (orientationStyle) {
            LinearLayout.HORIZONTAL -> {
                for (i in 0 until count) {
                    if (smoothAnimation) {
                        canvas.drawCircle((paddingLeft + circleRadioSize * (2 * i + 1) + circleSpace * i), (paddingTop + circleRadioSize), circleRadioSize, normalPaint)
                    } else {
                        if (i == currentPosition) {
                            canvas.drawCircle((paddingLeft + circleRadioSize * (2 * i + 1) + circleSpace * i), (paddingTop + circleRadioSize), circleRadioSize, selectedPaint)
                        } else {
                            canvas.drawCircle((paddingLeft + circleRadioSize * (2 * i + 1) + circleSpace * i), (paddingTop + circleRadioSize), circleRadioSize, normalPaint)
                        }
                    }

                }
                if (smoothAnimation) {
                    canvas.drawCircle((paddingLeft + circleRadioSize * (2 * currentPosition + 1) + circleSpace * currentPosition) + offset * (circleRadioSize * 2 + circleSpace), (paddingTop + circleRadioSize), circleRadioSize, selectedPaint)
                }
            }
            LinearLayout.VERTICAL -> {
                for (i in 0 until count) {
                    if (smoothAnimation) {
                        canvas.drawCircle((paddingLeft + circleRadioSize), (paddingTop + circleRadioSize * (2 * i + 1) + circleSpace * i), circleRadioSize, normalPaint)
                    } else {
                        if (i == currentPosition) {
                            canvas.drawCircle((paddingLeft + circleRadioSize), (paddingTop + circleRadioSize * (2 * i + 1) + circleSpace * i), circleRadioSize, selectedPaint)
                        } else {
                            canvas.drawCircle((paddingLeft + circleRadioSize), (paddingTop + circleRadioSize * (2 * i + 1) + circleSpace * i), circleRadioSize, normalPaint)
                        }
                    }

                }
                if (smoothAnimation) {
                    canvas.drawCircle((paddingLeft + circleRadioSize), (paddingTop + circleRadioSize * (2 * currentPosition + 1) + circleSpace * currentPosition) + offset * (circleRadioSize * 2 + circleSpace), circleRadioSize, selectedPaint)
                }
            }
        }
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec
     * A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private fun measureLong(measureSpec: Int): Int {
        var result: Int
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)

        if (specMode == View.MeasureSpec.EXACTLY || viewPager == null) {
            //We were told how big to be
            result = specSize
        } else {
            //Calculate the width according the views count
            val count = viewPager?.adapter?.count ?: 0
            result = when (orientationStyle) {
                LinearLayout.VERTICAL -> {
                    (paddingBottom + paddingTop + count * 2 * circleRadioSize + (count - 1) * circleSpace + 1).toInt()
                }
                LinearLayout.HORIZONTAL -> {
                    (paddingLeft + paddingRight + count * 2 * circleRadioSize + (count - 1) * circleSpace + 1).toInt()
                }
                else -> {
                    (paddingLeft + paddingRight + count * 2 * circleRadioSize + (count - 1) * circleSpace + 1).toInt()
                }

            }

            //Respect AT_MOST value if that was what is called for by measureSpec
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    /**
     * Determines the height of this view
     *
     * @param measureSpec
     * A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private fun measureShort(measureSpec: Int): Int {
        var result: Int
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)

        if (specMode == View.MeasureSpec.EXACTLY) {
            //We were told how big to be
            result = specSize
        } else {
            result = when (orientationStyle) {
                LinearLayout.VERTICAL -> {
                    (2 * circleRadioSize + paddingLeft + paddingRight + 1).toInt()
                }
                LinearLayout.HORIZONTAL -> {
                    (2 * circleRadioSize + paddingTop + paddingBottom + 1).toInt()
                }
                else -> {
                    (2 * circleRadioSize + paddingTop + paddingBottom + 1).toInt()
                }

            }
            //Measure the height

            //Respect AT_MOST value if that was what is called for by measureSpec
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    fun start() {
        if (viewPager == null) {
            throw RuntimeException("use NormalIndicatorView before ,please setViewPager")
            return
        }
        adapter = viewPager?.adapter
        count = adapter?.count ?: 0

        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                currentPosition = p0
                offset = p1
                invalidate()
            }

            override fun onPageSelected(p0: Int) {
            }
        })
    }

    companion object {
        const val ORIENTATION_DEFAULT = LinearLayout.HORIZONTAL
    }
}
