package com.example.skilltap.advertDetail

import com.example.skilltap.ui.advertDetail.AdvertDetail
import com.example.skilltap.ui.advertDetail.AdvertDetailServiceInterface
import com.example.skilltap.ui.advertDetail.Freelancer
import com.example.skilltap.ui.advertDetail.Package

class MockAdvertDetailService : AdvertDetailServiceInterface {
    var error : Boolean = false
    private var advertDetail :  Pair<AdvertDetail?,Boolean> = Pair(null,true)
    override suspend fun fetchAdvertDetail(advertId: Int) {
       if (error){
           advertDetail = Pair(null,true)
       }else{
           val detail : AdvertDetail = AdvertDetail(
               1,
               "",
               freelancer = Freelancer(1,"","","",""),
               packages = listOf(
                 Package(1,"","","",500),
                   Package(2,"","","",750),
                   Package(2,"","","",1000),

               )

           )
           advertDetail = Pair(detail,false)
       }
    }

    override suspend fun getAdvertDetail(): Pair<AdvertDetail?, Boolean> {
      return  advertDetail
    }
}