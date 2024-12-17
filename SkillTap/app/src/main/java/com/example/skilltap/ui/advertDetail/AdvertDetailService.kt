package com.example.skilltap.ui.advertDetail

import com.example.skilltap.retrofit.ApiService
import javax.inject.Inject

interface  AdvertDetailServiceInterface {
    suspend fun fetchAdvertDetail(advertId:Int)
    suspend fun getAdvertDetail() : Pair<AdvertDetail?,Boolean>
}


class AdvertDetailService @Inject constructor(
    private val apiService: ApiService
) : AdvertDetailServiceInterface {
    private var advertDetail :  Pair<AdvertDetail?,Boolean> = Pair(null,true)
    override suspend fun fetchAdvertDetail(advertId: Int) {
        try {
            val response = apiService.getAdvertDetail(advertId)
            if (response.isSuccessful){
                val detail = response.body()
                advertDetail = detail?.let {
                    Pair(it,false)
                } ?:  Pair(null,true)
            }else{
                Pair(null,true)
            }

        }catch (t:Throwable){
            advertDetail = Pair(null,true)
        }
    }

    override suspend fun getAdvertDetail(): Pair<AdvertDetail?, Boolean> {
       return  advertDetail
    }

}