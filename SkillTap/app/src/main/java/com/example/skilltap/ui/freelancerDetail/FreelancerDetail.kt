package com.example.skilltap.ui.freelancerDetail

import com.example.skilltap.ui.search.models.Advert

data class FreelancerDetail (
    val id: Long,
    val imageURL: String,
    val title: String,
    val subTitle: String,
    val detail: String,
    val rating: Double,
    val adverts: List<Advert>
)
