package com.example.skilltap.ui.search

import com.example.skilltap.retrofit.ApiService
import com.example.skilltap.ui.search.models.Advert
import com.example.skilltap.ui.search.models.Freelancer
import javax.inject.Inject

interface SearchServiceInterface {
    suspend fun fetchAllAdverts()
    suspend fun getAllAdverts() : Pair<List<Advert>,Boolean>

    suspend fun fetchAllFreelancers()
    suspend fun getAllFreelancer() : Pair<List<Freelancer>,Boolean>
}

class SearchService @Inject constructor(
    private val apiService: ApiService
) : SearchServiceInterface {

    private  var advertList : Pair<List<Advert>,Boolean> = Pair(emptyList(),false)
    private  var freelancerList : Pair<List<Freelancer>,Boolean> = Pair(emptyList(),false)
    override suspend fun fetchAllAdverts() {
        try {
            val response = apiService.getAllAdverts()
            if (response.isSuccessful){
                val list = response.body()
                advertList = list?.let {
                    Pair(it,false)
                }?: Pair(emptyList(),true)
            }else{
                advertList =  Pair(emptyList(),true)
            }
        }catch(t:Throwable){
            advertList =  Pair(emptyList(),true)
        }
    }

    override suspend fun getAllAdverts(): Pair<List<Advert>, Boolean> {
        return  advertList
    }

    override suspend fun fetchAllFreelancers() {
        try {
            val response = apiService.getAllFreelancers()
            if (response.isSuccessful){
                val list = response.body()
                freelancerList = list?.let {
                    Pair(it,false)
                }?: Pair(emptyList(),true)
            }else{
                freelancerList =  Pair(emptyList(),true)
            }
        }catch(t:Throwable){
            freelancerList =  Pair(emptyList(),true)
        }
    }

    override suspend fun getAllFreelancer(): Pair<List<Freelancer>, Boolean> {
        return freelancerList;
    }
}