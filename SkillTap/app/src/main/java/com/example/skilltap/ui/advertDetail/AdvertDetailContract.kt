package com.example.skilltap.ui.advertDetail

import com.example.skilltap.R
import com.example.skilltap.ui.search.SearchContract

object AdvertDetailContract {
    data class UiState (
        var packageIncludedTitle:Int = R.string.empty,
        var advertDescTitle:Int = R.string.empty,
        var firstOptionTitle:Int = R.string.empty,
        var secondOptionTitle:Int = R.string.empty,
        var thirdOptionTitle:Int = R.string.empty,
        var navigationState:Boolean = false,
        var navTitle:Int = R.string.empty
    )


    data class AdvertDetailState(
       var advertDetail:AdvertDetail? = null,
        var errorState:Boolean = true,
        var message:Int = R.string.empty
    )

    data class PackageIncludeState(
        var tite:String = "",
        var desc:String = "",
        var price:String = ""
    )

    sealed interface UiAction {
        data class clicledRadionButton(val index:Int) : UiAction
    }
}