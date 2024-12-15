package com.example.skilltap.ui.home

import android.util.Log
import com.example.skilltap.retrofit.ApiService
import javax.inject.Inject

interface HomeServiceInterface {
    suspend  fun fetchAllCategories()
    suspend fun getAllCategories() : Pair<List<Category>,Boolean>
}

class HomeService @Inject constructor(private val apiService: ApiService) : HomeServiceInterface {
    private var categoryList : Pair<List<Category>,Boolean> = Pair(emptyList(),false)
    override suspend fun fetchAllCategories() {
        try {
            val response = apiService.getAllCategories()
            if (response.isSuccessful){
                val list = response.body()
                categoryList = list?.let {
                    Pair(it, false)
                } ?: Pair(emptyList(), true)
            }else{
                categoryList = Pair(emptyList(), true)
            }
        }catch (t:Throwable){
            categoryList = Pair(emptyList(), true)

        }
    }

    override suspend fun getAllCategories(): Pair<List<Category>,Boolean> {
        return  categoryList
    }

}