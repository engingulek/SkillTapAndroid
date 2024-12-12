package com.example.skilltap.ui.home

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface  HomeViewModelInterface {
    var uiState : LiveData<HomeContract.UiState>

}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private  var service: HomeServiceInterface
) : ViewModel(),HomeViewModelInterface {
    private var _uiState = MutableLiveData(HomeContract.UiState())
    override var uiState : LiveData<HomeContract.UiState> = _uiState
    private  var categoryList : List<Category> = emptyList()

    init {
        setUiState()
        fetchData()
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


    private fun fetchData(){
        fetchCategories()
    }

    // fetch categories
    private  fun fetchCategories(){
        viewModelScope.launch {
            service.fetchAllCategories()
            val list = service.getAllCategories()
            _uiState.value = _uiState.value?.copy(
                categoryList = list
            )
        }
    }
}