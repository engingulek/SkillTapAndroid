package com.example.skilltap.ui.advertDetail

import com.google.gson.annotations.SerializedName


data class AdvertDetail (
    val id: Int,
    val image: String,
    val freelancer: Freelancer,
    val packages: List<Package>
)

data class Freelancer (
    val id: Int,
    val imageURL: String,
    val title: String,
    val subTitle: String,
    val detail: String
)

data class Package (
    val id: Int,
    val title: String,
    @SerializedName("package_type")
    val packageType: String,
    val detail: String,
    val price: Int
)