package com.example.skilltap.retrofit

import com.example.skilltap.ui.advertDetail.AdvertDetail
import com.example.skilltap.ui.freelancerDetail.FreelancerDetail
import com.example.skilltap.ui.home.Category
import com.example.skilltap.ui.search.models.Advert
import com.example.skilltap.ui.search.models.Freelancer
import com.example.skilltap.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.categories + Constants.getAll)
    suspend fun getAllCategories() : Response<List<Category>>

    @GET(Constants.adverts + Constants.getAll)
    suspend fun getAllAdverts() : Response<List<Advert>>

    @GET(Constants.freelancer + Constants.getAll)
    suspend fun getAllFreelancers() : Response<List<Freelancer>>

    @GET(Constants.adverts + Constants.advertDetail)
    suspend fun getAdvertDetail(@Query("id") id:Int) : Response<AdvertDetail>

    @GET(Constants.freelancer + Constants.getFreelancerDetail)
    suspend fun getFreelancerDetail(@Query("id") id:Int) : Response<FreelancerDetail>
}