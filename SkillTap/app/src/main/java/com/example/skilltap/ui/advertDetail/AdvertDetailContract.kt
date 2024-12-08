package com.example.skilltap.ui.advertDetail

import com.example.skilltap.R
import com.example.skilltap.ui.search.SearchContract

object AdvertDetailContract {
    data class UiState (
        var packageIncludedTitle:Int = R.string.empty,
        var advertDescTitle:Int = R.string.empty,
        var firstOptionTitle:Int = R.string.empty,
        var secondOptionTitle:Int = R.string.empty,
        var thirdOptionTitle:Int = R.string.empty
    )

   /* data class OptionType(
        var title:Int = R.string.empty,
        var checked:Boolean = false
    )*/

    sealed interface UiAction {
        data class clicledRadionButton(val index:Int) : UiAction
    }
}