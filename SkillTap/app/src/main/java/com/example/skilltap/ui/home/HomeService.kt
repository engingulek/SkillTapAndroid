package com.example.skilltap.ui.home

import android.util.Log
import com.example.skilltap.retrofit.ApiService
import javax.inject.Inject

interface HomeServiceInterface {
    suspend  fun fetchAllCategories()
    suspend fun getAllCategories() : List<Category>
}

class HomeService @Inject constructor(private val apiService: ApiService) : HomeServiceInterface {
    private var categoryList : List<Category> = emptyList()
    override suspend fun fetchAllCategories() {
        try {
            val response = apiService.getAllCategories()
            if (response.isSuccessful){
                val list = response.body()
                list?.let {
                    categoryList = it
                }
            }else{
                categoryList = emptyList()
            }
        }catch (t:Throwable){
            categoryList = emptyList()
            Log.e("Category List Error","${t.message}")
        }
    }

    override suspend fun getAllCategories(): List<Category> {
        return  categoryList
    }

}