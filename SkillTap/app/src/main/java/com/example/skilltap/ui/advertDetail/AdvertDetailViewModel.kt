package com.example.skilltap.ui.advertDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skilltap.R
import com.example.skilltap.ui.search.SearchContract

interface AdvertDetailViewModelInterface {
    var uiState : LiveData<AdvertDetailContract.UiState>
    fun onAction(action:AdvertDetailContract.UiAction)
}

class AdvertDetailViewModel : ViewModel(),AdvertDetailViewModelInterface {
    private  var _uiState = MutableLiveData(AdvertDetailContract.UiState())
    override var uiState : LiveData<AdvertDetailContract.UiState> = _uiState

    init {
        setFirstUiState()
    }

    private fun setFirstUiState() {
        _uiState.value = AdvertDetailContract.UiState(
            packageIncludedTitle = R.string.packageIncludedTitle,
            advertDescTitle = R.string.advertdesc,
            firstOptionTitle = R.string.basis,
            secondOptionTitle = R.string.standard,
            thirdOptionTitle = R.string.pro
        )
    }

    override fun onAction(action: AdvertDetailContract.UiAction) {
        when(action){
            is AdvertDetailContract.UiAction.clicledRadionButton ->changedRadioButton(action.index)
        }
    }

    fun changedRadioButton(index: Int) {
       Log.e("Selected","$index")

    }
}