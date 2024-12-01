package com.example.skilltap.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface  HomeViewModelInterface {
    var uiState : LiveData<HomeContract.UiState>
    fun onAction(action:HomeContract.UiAction)
}

class HomeViewModel : ViewModel(),HomeViewModelInterface {
    private var _uiState = MutableLiveData(HomeContract.UiState())
    override var uiState : LiveData<HomeContract.UiState> = _uiState

    init {
        setUiState()
    }

    private fun setUiState(){
        _uiState.value = HomeContract.UiState(
            title = "Hi,Engin",
            subTitle = "What you are looking for is here",
            searchbarPlaceHolder = "What do you need?",
            freelancerBannerView = HomeContract.BannerView(
                "",
                "Adverts",
                "5k adverts"),
            advertBannerView = HomeContract.BannerView(
                "",
                "Find Freelancer",
                "120k freelancer")
        )
    }

    override fun onAction(action: HomeContract.UiAction) {
        when(action){
            is HomeContract.UiAction.clickedEstateType -> clickedSearchbar()
        }
    }


    fun clickedSearchbar(){
        Log.e("Search bar","clicked")
    }

}