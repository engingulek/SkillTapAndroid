package com.example.skilltap.retrofit

import com.example.skilltap.ui.home.Category
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("categories/getAll")
    suspend fun getAllCategories() : Response<List<Category>>
}