package com.example.skilltap.search

import com.example.skilltap.ui.search.SearchServiceInterface
import com.example.skilltap.ui.search.models.Advert
import com.example.skilltap.ui.search.models.Freelancer

class MockSearchService : SearchServiceInterface {
    var advertError : Boolean = false
   private  var advertList : Pair<List<Advert>,Boolean> =  Pair(emptyList(),false)

    var freelancerError : Boolean = false
  private  var freelancerList : Pair<List<Freelancer>,Boolean> =  Pair(emptyList(),false)
    override suspend fun fetchAllAdverts() {
        if (advertError){
            advertList =  Pair(emptyList(),true)
        }else{
            val list : List<Advert> =
                listOf(
                    Advert(1,"","title",250,"This is a developer advert")
                )
            advertList = Pair(list,false)
        }
    }

    override suspend fun getAllAdverts(): Pair<List<Advert>, Boolean> {
        return  advertList
    }

    override suspend fun fetchAllFreelancers() {
        if (freelancerError){
            freelancerList = Pair(emptyList(),true)
        }else{
            val list : List<Freelancer> = listOf(
                Freelancer(1,"URL","Mark Jane",
                    "subtitle","detail",4.5)
            )

            freelancerList = Pair(list,false)
        }
    }

    override suspend fun getAllFreelancer(): Pair<List<Freelancer>, Boolean> {
        return  freelancerList
    }
}