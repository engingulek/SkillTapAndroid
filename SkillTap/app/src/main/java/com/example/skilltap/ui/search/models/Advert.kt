package com.example.skilltap.ui.search.models

import com.google.gson.annotations.SerializedName

data class Advert (
    @SerializedName("id")  val id: Int,
    @SerializedName("imageURL") val imageURL: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Long,
    @SerializedName("detail") val detail: String
)
