package com.example.skilltap.ui.freelancerDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skilltap.R
import com.example.skilltap.ui.advertDetail.AdvertDetailContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


interface FreelancerDetailViewModelInterface {
    var uiState : LiveData<FreelancerDetailContract.UiState>
    var freelancerDetailState : LiveData<FreelancerDetailContract.FreelancerDetailState>
    fun getFreelancerDetail(id: Int)
}

@HiltViewModel
class FreelancerDetailViewModel @Inject constructor(
    private  var service: FreelancerDetailServiceInterface
):
    ViewModel(),FreelancerDetailViewModelInterface {
    private  var _uiState = MutableLiveData(FreelancerDetailContract.UiState())
    override var uiState : LiveData<FreelancerDetailContract.UiState> = _uiState

    private var _freelancerDetailState = MutableLiveData(FreelancerDetailContract.FreelancerDetailState())
    override var freelancerDetailState : LiveData<FreelancerDetailContract.FreelancerDetailState> = _freelancerDetailState


    init {
        firstUiState()
    }

    override fun getFreelancerDetail(id: Int) {
        viewModelScope.launch {
            service.fetchFreelancer(id)
            val detail = service.getFreelancerDetail()

            _freelancerDetailState.value = _freelancerDetailState.value?.copy(
                freelancerDetail = if(detail.second) null else detail.first,
                errorState = detail.second,
                message = if (detail.second) R.string.errorMessage else R.string.errorMessage
            )
        }
    }

    private fun firstUiState() {
        _uiState.value = FreelancerDetailContract.UiState(
            advertsTitle = R.string.adverts
        )
    }
}