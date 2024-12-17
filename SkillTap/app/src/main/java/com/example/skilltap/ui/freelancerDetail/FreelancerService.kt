package com.example.skilltap.ui.freelancerDetail

import com.example.skilltap.retrofit.ApiService
import javax.inject.Inject

interface FreelancerDetailServiceInterface {
    suspend fun fetchFreelancer(id:Int)
    suspend fun getFreelancerDetail() : Pair<FreelancerDetail?,Boolean>

}

class FreelancerDetailService @Inject constructor(
    private val apiService: ApiService
) : FreelancerDetailServiceInterface {
    private var freelancerDetail :  Pair<FreelancerDetail?,Boolean> = Pair(null,true)
    override suspend fun fetchFreelancer(id: Int) {
        try {
            val response = apiService.getFreelancerDetail(id)
            if (response.isSuccessful){
                val detail = response.body()
                freelancerDetail = detail?.let {
                    Pair(it,false)
                } ?:  Pair(null,true)
            }else{
                Pair(null,true)
            }

        }catch (t:Throwable){
            freelancerDetail = Pair(null,true)
        }
    }

    override suspend fun getFreelancerDetail(): Pair<FreelancerDetail?, Boolean> {
      return freelancerDetail
    }

}