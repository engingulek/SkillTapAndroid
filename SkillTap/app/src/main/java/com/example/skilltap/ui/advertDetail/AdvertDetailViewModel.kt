package com.example.skilltap.ui.advertDetail


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skilltap.R

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


interface AdvertDetailViewModelInterface  {
    var uiState : LiveData<AdvertDetailContract.UiState>
    var advertDetailState : LiveData<AdvertDetailContract.AdvertDetailState>
    var packageIncludeState: LiveData<AdvertDetailContract.PackageIncludeState>
    fun onAction(action:AdvertDetailContract.UiAction)

    fun getAdvertId(advertId:Int)

}

@HiltViewModel
class AdvertDetailViewModel @Inject constructor(
    private  var service:AdvertDetailServiceInterface
) :
    ViewModel(),AdvertDetailViewModelInterface {
    private  var _uiState = MutableLiveData(AdvertDetailContract.UiState())
    override var uiState : LiveData<AdvertDetailContract.UiState> = _uiState

    private  var _advertDetailState = MutableLiveData(AdvertDetailContract.AdvertDetailState())
    override   var advertDetailState : LiveData<AdvertDetailContract.AdvertDetailState> = _advertDetailState

    private  var _packageIncludeState = MutableLiveData(AdvertDetailContract.PackageIncludeState())
    override   var packageIncludeState: LiveData<AdvertDetailContract.PackageIncludeState> = _packageIncludeState



    init {
        setFirstUiState()
    }

    private fun setFirstUiState() {
        _uiState.value = AdvertDetailContract.UiState(
            packageIncludedTitle = R.string.packageIncludedTitle,
            advertDescTitle = R.string.desc,
            firstOptionTitle = R.string.basis,
            secondOptionTitle = R.string.standard,
            thirdOptionTitle = R.string.pro,
            navigationState = true,
            navTitle = R.string.advertDetailNav
        )
    }

    override fun onAction(action: AdvertDetailContract.UiAction) {
        when(action){
            is AdvertDetailContract.UiAction.clicledRadionButton ->changedRadioButton(action.index)
        }
    }

    override fun getAdvertId(advertId: Int) {
        viewModelScope.launch {
            service.fetchAdvertDetail(advertId)
            val advertDetail = service.getAdvertDetail()

          _advertDetailState.value =  _advertDetailState.value?.copy(
                advertDetail = if (advertDetail.second) null else advertDetail.first,
                errorState =  advertDetail.second,
                message = if (advertDetail.second) R.string.errorMessage else R.string.errorMessage
            )

            _packageIncludeState.value = _packageIncludeState.value?.copy(
                tite = advertDetail.first?.packages?.get(0)?.title ?: "",
                desc = advertDetail.first?.packages?.get(0)?.detail ?: "",
                price = "${advertDetail.first?.packages?.get(0)?.price} ₺"
            )
        }
    }

   private  fun changedRadioButton(index: Int) {


    _packageIncludeState.value = _packageIncludeState.value?.copy(
        tite = _advertDetailState.value?.advertDetail?.packages?.get(index)?.title ?: "",
        desc = _advertDetailState.value?.advertDetail?.packages?.get(index)?.detail ?: "",
        price = "${_advertDetailState.value?.advertDetail?.packages?.get(index)?.price} ₺"
    )

    }
}