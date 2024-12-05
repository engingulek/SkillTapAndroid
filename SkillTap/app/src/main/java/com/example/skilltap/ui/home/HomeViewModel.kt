package com.example.skilltap.ui.home

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface  HomeViewModelInterface {
    var uiState : LiveData<HomeContract.UiState>

}

class HomeViewModel() : ViewModel(),HomeViewModelInterface {
    private var _uiState = MutableLiveData(HomeContract.UiState())
    override var uiState : LiveData<HomeContract.UiState> = _uiState

    init {
        setUiState()
    }

    private fun setUiState(){
        //TODO: string value
        _uiState.value = HomeContract.UiState(
            title = "Hi,Engin",
            subTitle = "What you are looking for is here",
            searchbarPlaceHolder = "What do you need?",
            categoryTitle = "Categories"
        )
    }


}