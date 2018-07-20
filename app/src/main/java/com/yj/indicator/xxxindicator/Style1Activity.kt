package com.yj.indicator.xxxindicator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yj.indicator.xxxindicator.adapter.TourismAdapter
import com.yj.indicator.xxxindicator.bean.TourismResponse
import kotlinx.android.synthetic.main.activity_style1.*
import java.util.ArrayList

class Style1Activity : AppCompatActivity() {
    private val tourismAdapter by lazy {
        TourismAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_style1)
        val arrayList = Gson().fromJson<List<TourismResponse>>(json, (object : TypeToken<List<TourismResponse>>() {}).type)
        tourismAdapter.replaceItems(arrayList as ArrayList<TourismResponse>)

        view_pager.adapter = tourismAdapter
        indicator_view.apply {
            viewPager=view_pager
            orientationStyle= LinearLayout.HORIZONTAL
            indicatorPosition= Gravity.LEFT
            mIndicatorLeftMargin= 5f
            mIndicatorRightMargin= 5f
            mIndicatorSize= 10f
            mIndicatorSelectedResId= R.drawable.gray_radius1
            mIndicatorUnselectedResId= R.drawable.white_radius1
        }
        indicator_view.start()
        view_pager.startAutoScroll()
        view_pager.isBorderAnimation=false
        view_pager.interval = 3000
        view_pager.setScrollDurationFactor(3.0)

    }
    companion object {
        const val json = "[{\"id\":90,\"image_url\":[\"http://sky.klicen.com/media/help/content_photo/41ed59e8-b7b.jpg\",\"http://sky.klicen.com/media/help/content_photo/173f0ef8-a6f.jpg\",\"http://sky.klicen.com/media/help/content_photo/de1f76aa-947.jpg\",\"http://sky.klicen.com/media/help/content_photo/efee3f95-7c2.jpg\"],\"description\":\"兴义万峰林，天下山峰何其多，唯有此处峰成林\",\"tag\":[\"美食\",\"美景\"],\"time\":\"2018-04-06 20:00:00\",\"place\":\"兴义万峰林\",\"user_name\":\"天边シ深海\",\"brand\":\"雪佛兰\",\"serial\":\"科沃兹\",\"user_photo\":\"http://c.klicen.com/media/random_headphoto/0569.jpg\",\"title\":\"多彩黔行，探寻山林秘境——凯励程贵州自驾游\",\"skip_url\":\"http://c.klicen.com/web_items/selfDriving/dist/index.html#/sd-detail?id=54\"},{\"id\":96,\"image_url\":[\"http://sky.klicen.com/media/help/content_photo/9cbf97eb-4c1.jpg\",\"http://sky.klicen.com/media/help/content_photo/02773845-f99.jpg\",\"http://sky.klicen.com/media/help/content_photo/68daac78-618.jpg\",\"http://sky.klicen.com/media/help/content_photo/747853bb-073.jpg\",\"http://sky.klicen.com/media/help/content_photo/519f5ad4-994.jpg\",\"http://sky.klicen.com/media/help/content_photo/a3692566-c7b.jpg\"],\"description\":\"跟着凯励程自驾团来到了“草原天路”，第一站便是花的海洋——俄么塘花海，在一望无际的草原上，五颜六色的花朵显得愈加迷人，我的心情也变得开朗起来。期待明日的行程！\",\"tag\":[\"美景\",\"自然\"],\"time\":\"2018-06-16 22:00:00\",\"place\":\"俄么塘花海\",\"user_name\":\"知我几分\",\"brand\":\"日产\",\"serial\":\"奇骏\",\"user_photo\":\"http://c.klicen.com/media/random_headphoto/0706.jpg\",\"title\":\"端午小长假若尔盖-俄木塘花海-九曲黄河，策马奔腾，红尘作伴！\",\"skip_url\":\"http://c.klicen.com/web_items/selfDriving/dist/index.html#/sd-detail?id=64\"},{\"id\":100,\"image_url\":[\"http://sky.klicen.com/media/help/content_photo/6eb598f4-251.jpg\",\"http://sky.klicen.com/media/help/content_photo/b1b837e4-b1b.jpg\",\"http://sky.klicen.com/media/help/content_photo/a3ffa028-0ed.jpg\"],\"description\":\"小时候曾有一个做神枪手的梦想，感受一发即中的兴奋与疯狂。把真枪拿捏在手中，体验真枪实弹的快感！结果长大后在泰国感受到了实弹射击的刺激，满足了！\",\"tag\":[\"人文\"],\"time\":\"2018-06-16 13:03:00\",\"place\":\"是拉差\",\"user_name\":\"不败君王\",\"brand\":\"起亚\",\"serial\":\"K5\",\"user_photo\":\"http://c.klicen.com/media/random_headphoto/1054.jpg\",\"title\":\"凯励程泰国落地自驾第二波来袭！6天5晚，￥2999/成人，超高性价比！\",\"skip_url\":\"http://c.klicen.com/web_items/selfDriving/dist/index.html#/sd-detail?id=66\"},{\"id\":102,\"image_url\":[\"http://sky.klicen.com/media/help/content_photo/633d5b61-9f1.jpg\",\"http://sky.klicen.com/media/help/content_photo/0f8f9b44-d49.jpg\",\"http://sky.klicen.com/media/help/content_photo/b7021d30-7b4.jpg\"],\"description\":\"我们一起上了山，下了海，也登上了泰国最高楼，自驾最原生态象岛。浮潜、骑大象、海鲜大餐......这个假期，真的很棒啊，在此感谢凯励程组织的自驾游活动。下次什么时候再组织境外自驾？\",\"tag\":[\"美食\",\"美景\",\"人文\"],\"time\":\"2018-06-20 10:00:00\",\"place\":\"曼谷\",\"user_name\":\"Redundant°\",\"brand\":\"大众\",\"serial\":\"朗行\",\"user_photo\":\"http://c.klicen.com/media/user_headphoto/originals/2015/10/25/0131.jpg\",\"title\":\"凯励程泰国落地自驾第二波来袭！6天5晚，￥2999/成人，超高性价比！\",\"skip_url\":\"http://c.klicen.com/web_items/selfDriving/dist/index.html#/sd-detail?id=66\"},{\"id\":106,\"image_url\":[\"http://sky.klicen.com/media/help/content_photo/fa2628b2-1e0.jpg\",\"http://sky.klicen.com/media/help/content_photo/f03fec3c-f53.jpg\",\"http://sky.klicen.com/media/help/content_photo/de5fef8e-f95.jpg\"],\"description\":\"犹如田园牧歌般的童话世界，等有机会，我一定还要再来甲居藏寨住。\",\"tag\":[\"人文\"],\"time\":\"2018-06-17 17:00:00\",\"place\":\"丹巴\",\"user_name\":\"蝉鸣半夏\",\"brand\":\"荣威\",\"serial\":\"RX5\",\"user_photo\":\"http://c.klicen.com/media/random_headphoto/1444.jpg\",\"title\":\"端午小长假川西小环线，经典线路自驾游\",\"skip_url\":\"http://c.klicen.com/web_items/selfDriving/dist/index.html#/sd-detail?id=62\"}]"
    }
}
