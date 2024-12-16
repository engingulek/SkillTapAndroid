package com.example.skilltap.ui.search.models

import com.google.gson.annotations.SerializedName

data class Freelancer (
    @SerializedName("id")  val id: Int,
    @SerializedName("imageURL") val imageURL: String,
    @SerializedName("title") val title: String,
    @SerializedName("subTitle") val subTitle: String,
    @SerializedName("detail") val detail: String,
    @SerializedName("rating") val rating: Double
)
