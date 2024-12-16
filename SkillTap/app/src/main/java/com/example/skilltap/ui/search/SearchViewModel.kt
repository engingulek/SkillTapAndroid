package com.example.skilltap.ui.search
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skilltap.R
import com.example.skilltap.ui.home.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


interface  SearchViewModelInterface {
    var uiState : LiveData<SearchContract.UiState>
    var advertsButtonsState  : LiveData<SearchContract.AdvertButtonState>
    var freelancerButtonState : LiveData<SearchContract.FreelancerButtonState>
    var advertDataState : LiveData<SearchContract.AdvertDataState>
    var freelancerDataState : LiveData<SearchContract.FreelancerDataState>
    fun onAction(action:SearchContract.UiAction)
}

@HiltViewModel
class SearchViewModel @Inject constructor(private  var service:SearchServiceInterface)
    : ViewModel(),
    SearchViewModelInterface {
    private var _uiState = MutableLiveData(SearchContract.UiState())
    override var uiState : LiveData<SearchContract.UiState> = _uiState

    private var _advertsButtonsState = MutableLiveData(SearchContract.AdvertButtonState())
   override var advertsButtonsState  : LiveData<SearchContract.AdvertButtonState> = _advertsButtonsState

    private var _freelancerButtonState = MutableLiveData(SearchContract.FreelancerButtonState())
    override var freelancerButtonState  : LiveData<SearchContract.FreelancerButtonState> = _freelancerButtonState

    private  var _advertDataState = MutableLiveData(SearchContract.AdvertDataState())
    override var advertDataState: LiveData<SearchContract.AdvertDataState> = _advertDataState

    private  var _freelancerDataState = MutableLiveData(SearchContract.FreelancerDataState())
    override var freelancerDataState : LiveData<SearchContract.FreelancerDataState> = _freelancerDataState
    init {
        setUiState()
        fetchData()
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

    private fun fetchData(){
        fetchAdverts()
        fetchFreelancer()
    }

    // fetchAdverts
    private fun fetchAdverts(){
        viewModelScope.launch {
            service.fetchAllAdverts()
            val item = service.getAllAdverts()
            _advertDataState.value = SearchContract.AdvertDataState(
                list = item.first,
                errorState = item.second,
                message =  if (item.second) R.string.errorMessage
                else R.string.empty
            )


        }
    }

    // fetchFreelancer
    private fun fetchFreelancer(){
        viewModelScope.launch {
            service.fetchAllFreelancers()
            val item = service.getAllFreelancer()
            _freelancerDataState.value = SearchContract.FreelancerDataState(
                list = item.first,
                errorState = item.second,
                message = if (item.second) R.string.errorMessage
                else R.string.empty
            )

        }
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