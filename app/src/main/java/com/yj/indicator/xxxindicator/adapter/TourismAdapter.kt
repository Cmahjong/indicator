package com.yj.indicator.xxxindicator.adapter

import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yj.banner.xxxbanner.util.GlideUtil
import com.yj.banner.xxxbanner.util.SystemUtil
import com.yj.indicator.xxxindicator.R

import com.yj.indicator.xxxindicator.bean.TourismResponse
import kotlinx.android.synthetic.main.item_tourism_k6.view.*
import java.util.*
import kotlin.collections.ArrayList


/**
 * desc:
 * time: 2018/5/9
 * @author yinYin
 */
class TourismAdapter : PagerAdapter() {
    val list by lazy {
        ArrayList<TourismResponse>()
    }
    private val mReusableViews: ArrayList<View> by lazy {
     arrayListOf<View> ()
    }

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

    override fun getCount() = list.size


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view: View?
        try {
            view=mReusableViews[position]
        } catch (e: Exception) {
            view = LayoutInflater.from(container?.context).inflate(R.layout.item_tourism_k6, container, false)
            initData(view, list[position])
            mReusableViews.add(view)
        }
        view!!.setOnClickListener {

        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    private fun initData(view: View, tourismResponse: TourismResponse) {
        view.apply {
            tv_title.text = tourismResponse.title?.takeIf { it.isNotBlank() }?.let { it } ?: ""
//            tv_title.text ="《先测试用先测试用先测试用先测试用先测试用先测试用先测试用llllllll》"
            tv_content.text = tourismResponse.description ?: ""
            tv_name.text = tourismResponse.userName ?: ""
            tv_address.text = tourismResponse.place ?: ""
            GlideUtil.clearLoad(context, img_head)
            GlideUtil.clearLoad(context, img_picture)
            GlideUtil.loadPicture(img_head, tourismResponse.userPhoto
                    ?: "", R.drawable.login_header_default)
            if (tourismResponse.imageUrl != null && tourismResponse.imageUrl.isNotEmpty()) {
                GlideUtil.loadRoundPicture(img_picture, tourismResponse.imageUrl[0]
                        ?: "", SystemUtil.dip2px(context, 5f).toFloat(), R.drawable.cover_main_banner_empty_holder)
            }

        }
    }

    fun replaceItems(receivedSharing: ArrayList<TourismResponse>) {
        list.clear()
        mReusableViews?.clear()
        list.addAll(receivedSharing)
        notifyDataSetChanged()
    }


}