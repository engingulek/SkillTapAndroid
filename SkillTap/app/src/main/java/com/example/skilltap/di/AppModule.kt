package com.example.skilltap.di

import com.example.skilltap.retrofit.ApiService
import com.example.skilltap.ui.advertDetail.AdvertDetailService
import com.example.skilltap.ui.advertDetail.AdvertDetailServiceInterface
import com.example.skilltap.ui.freelancerDetail.FreelancerDetailContract
import com.example.skilltap.ui.freelancerDetail.FreelancerDetailService
import com.example.skilltap.ui.freelancerDetail.FreelancerDetailServiceInterface
import com.example.skilltap.ui.home.HomeService
import com.example.skilltap.ui.home.HomeServiceInterface
import com.example.skilltap.ui.search.SearchService
import com.example.skilltap.ui.search.SearchServiceInterface
import com.example.skilltap.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApiService() : ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeService(apiService: ApiService) : HomeServiceInterface {
        return  HomeService(apiService)
    }


    @Provides
    @Singleton
    fun provideSearchService(apiService: ApiService) : SearchServiceInterface {
        return  SearchService(apiService)
    }

    @Provides
    @Singleton
    fun provideAdvertDetailService(apiService: ApiService) : AdvertDetailServiceInterface {
        return  AdvertDetailService(apiService)
    }


    @Provides
    @Singleton
    fun provideFreelancerDetailService(apiService: ApiService) : FreelancerDetailServiceInterface {
        return  FreelancerDetailService(apiService)
    }
}