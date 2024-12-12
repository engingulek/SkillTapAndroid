package com.example.skilltap.home

import com.example.skilltap.ui.home.Category
import com.example.skilltap.ui.home.HomeServiceInterface

class MockHomeService : HomeServiceInterface {
    var categoryError : Boolean = false
    private var categoryList : List<Category> = emptyList()
    override suspend fun fetchAllCategories() {
        if (categoryError){
            categoryList = emptyList()
        }else{
            val list : List<Category> = listOf(
                Category(1,"testtitle","testimageur",10,12,"colorcodetest"),
                Category(2,"testtitle1","testimageurl1",10,12,"colorcodetest1"),
            )

            categoryList = list
        }
    }

    override suspend fun getAllCategories(): List<Category> {
        return  categoryList
    }
}