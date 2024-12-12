package com.example.skilltap.ui.home

object HomeContract {

    data class UiState(
        var title:String = "",
        var subTitle:String = "",
        var searchbarPlaceHolder:String = "",
        var categoryTitle:String = "",
        var categoryList:List<Category> = emptyList()
    )

}