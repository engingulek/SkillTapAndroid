package com.example.skilltap.ui.home

import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("imageURL") val imageUrl:String,
    @SerializedName("advert_count") val advertCount:Int,
    @SerializedName("freelancer_count") val freelancerCount:Int,
    @SerializedName("color_code") val colorCode:String,
)