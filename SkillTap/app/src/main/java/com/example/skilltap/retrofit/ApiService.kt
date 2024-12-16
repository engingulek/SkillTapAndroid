package com.example.skilltap.retrofit

import com.example.skilltap.ui.home.Category
import com.example.skilltap.ui.search.models.Advert
import com.example.skilltap.ui.search.models.Freelancer
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("categories/getAll")
    suspend fun getAllCategories() : Response<List<Category>>

    @GET("adverts/getAll")
    suspend fun getAllAdverts() : Response<List<Advert>>

    @GET("freelancer/getAll")
    suspend fun getAllFreelancers() : Response<List<Freelancer>>
}