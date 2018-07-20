package com.yj.indicator.xxxindicator.bean

import com.google.gson.annotations.SerializedName

/**
 * desc:
 * time: 2018/5/10
 * @author yinYin
 */


data class TourismResponse(
        @SerializedName("id") val id: Int?,
        @SerializedName("image_url") val imageUrl: List<String?>?,
        @SerializedName("description") val description: String?,
        @SerializedName("tag") val tag: List<String?>?,
        @SerializedName("time") val time: String?,
        @SerializedName("place") val place: String?,
        @SerializedName("user_name") val userName: String?,
        @SerializedName("brand") val brand: String?,
        @SerializedName("serial") val serial: String?,
        @SerializedName("user_photo") val userPhoto: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("skip_url") val skipUrl: String?
)
