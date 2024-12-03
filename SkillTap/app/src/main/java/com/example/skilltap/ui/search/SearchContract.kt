package com.example.skilltap.ui.search

import com.example.skilltap.R
import com.example.skilltap.ui.home.HomeContract.BannerView

object SearchContract {

    data class UiState(
        var searchbarPlaceHolder:Int = R.string.empty,
        )

    sealed interface UiAction {
        data object clickedAdvertsBttn:UiAction
        data object clickedFreelancerBttn:UiAction
    }

    data class AdvertButtonState(
        var text:Int = R.string.empty,
        var selected:Boolean = false
    )

    data class FreelancerButtonState(
        var text: Int = R.string.empty,
        var selected:Boolean = false
    )

}