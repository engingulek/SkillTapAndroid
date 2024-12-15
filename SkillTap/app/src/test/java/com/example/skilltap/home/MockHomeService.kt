package com.example.skilltap.home

import com.example.skilltap.ui.home.Category
import com.example.skilltap.ui.home.HomeServiceInterface

class MockHomeService : HomeServiceInterface {
    var categoryError : Boolean = false
    var categoryList : Pair<List<Category>,Boolean> = Pair(emptyList(),false)
    override suspend fun fetchAllCategories() {
        if (categoryError){
            categoryList = Pair(emptyList(),true)
        }else{
            val list : List<Category> = listOf(
                Category(1,"testtitle","testimageur",10,12,"colorcodetest"),
                Category(2,"testtitle1","testimageurl1",10,12,"colorcodetest1"),
            )

            categoryList = Pair(list,false)
        }
    }

    override suspend fun getAllCategories(): Pair<List<Category>,Boolean> {
        return  categoryList
    }
}