package com.example.skilltap.ui.home

import com.example.skilltap.R

object HomeContract {

    data class UiState(
        var title:Int = R.string.empty,
        var subTitle:Int = R.string.empty,
        var searchbarPlaceHolder:Int = R.string.empty,
        var categoryTitle:Int = R.string.empty,
        var categoryList:List<Category> = emptyList()
    )

}