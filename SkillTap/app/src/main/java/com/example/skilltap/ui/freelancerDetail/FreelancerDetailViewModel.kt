package com.example.skilltap.ui.freelancerDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skilltap.R
import com.example.skilltap.ui.advertDetail.AdvertDetailContract


interface FreelancerDetailViewModelInterface {
    var uiState : LiveData<FreelancerDetailContract.UiState>
}


class FreelancerDetailViewModel:ViewModel(),FreelancerDetailViewModelInterface {
    private  var _uiState = MutableLiveData(FreelancerDetailContract.UiState())
    override var uiState : LiveData<FreelancerDetailContract.UiState> = _uiState

    init {
        firstUiState()
    }


    private fun firstUiState() {
        _uiState.value = FreelancerDetailContract.UiState(
            advertsTitle = R.string.adverts
        )
    }
}