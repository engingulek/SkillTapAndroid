package com.example.skilltap.ui.search
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skilltap.R
import com.example.skilltap.ui.home.HomeContract


interface  SearchViewModelInterface {
    var uiState : LiveData<SearchContract.UiState>
    var advertsButtonsState  : LiveData<SearchContract.AdvertButtonState>
    var freelancerButtonState : LiveData<SearchContract.FreelancerButtonState>
    fun onAction(action:SearchContract.UiAction)
}
class SearchViewModel : ViewModel(),SearchViewModelInterface {
    private var _uiState = MutableLiveData(SearchContract.UiState())
    override var uiState : LiveData<SearchContract.UiState> = _uiState

    private var _advertsButtonsState = MutableLiveData(SearchContract.AdvertButtonState())
   override var advertsButtonsState  : LiveData<SearchContract.AdvertButtonState> = _advertsButtonsState

    private var _freelancerButtonState = MutableLiveData(SearchContract.FreelancerButtonState())
    override var freelancerButtonState  : LiveData<SearchContract.FreelancerButtonState> = _freelancerButtonState



    init {
        setUiState()
    }

    private fun setUiState(){
        _uiState.value = SearchContract.UiState(
            searchbarPlaceHolder = R.string.searchPlaceholder
        )

        _advertsButtonsState.value = SearchContract.AdvertButtonState(
            text = R.string.adverts,
            selected = true
        )

        _freelancerButtonState.value = SearchContract.FreelancerButtonState(
            text = R.string.freelancers,
            selected = false
        )

    }

    override fun onAction(action: SearchContract.UiAction) {
        when (action){
            SearchContract.UiAction.clickedAdvertsBttn -> onClickedAdvertsBttn()
            SearchContract.UiAction.clickedFreelancerBttn -> onClickedFreelancerBttn()
        }
    }

    private fun onClickedAdvertsBttn(){
        _advertsButtonsState.value =  _advertsButtonsState.value?.copy(
            selected = true
        )

        _freelancerButtonState.value = _freelancerButtonState.value?.copy(
            selected = false
        )
    }

    private fun onClickedFreelancerBttn(){
        _advertsButtonsState.value =  _advertsButtonsState.value?.copy(
            selected = false
        )

        _freelancerButtonState.value = _freelancerButtonState.value?.copy(
            selected = true
        )
    }
}