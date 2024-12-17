package com.example.skilltap.ui.freelancerDetail

import com.example.skilltap.R

object FreelancerDetailContract {
    data class UiState(
        var advertsTitle:Int = R.string.empty,
    )

    data class FreelancerDetailState(
        var freelancerDetail: FreelancerDetail? = null,
        var errorState:Boolean = true,
        var message:Int = R.string.empty

    )
}