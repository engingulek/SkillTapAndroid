package com.example.skilltap.ui.home

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skilltap.R
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


    init {

        fetchData()
    }


    private fun fetchData(){
        fetchCategories()
    }

    // fetch categories
    private  fun fetchCategories(){
        _uiState.value = _uiState.value?.copy(
            loadingAction = true
        )
        viewModelScope.launch {
            service.fetchAllCategories()
            val item = service.getAllCategories()
            _uiState.value = _uiState.value?.copy(
                title = R.string.hi,
                subTitle = R.string.homeSubTittle,
                searchbarPlaceHolder = R.string.searchPlaceholder,
                categoryTitle = R.string.categories,
                categoryList = item.first,
                errorState = item.second,
                errorMessage = if(item.second) R.string.errorMessage else R.string.empty,
                loadingAction = false,
                navigationState = false,
                navTitle = R.string.homeNav
            )
        }
    }
}