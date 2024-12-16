package com.example.skilltap.ui.search
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skilltap.R
import com.example.skilltap.ui.home.HomeContract
import com.example.skilltap.ui.search.models.Advert
import com.example.skilltap.ui.search.models.Freelancer
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

    private  var tempAdvertList :List<Advert> = emptyList()
    private  var tempFreelancerList : List<Freelancer> = emptyList()
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
            tempAdvertList = item.first
            _advertDataState.value = SearchContract.AdvertDataState(
                list = item.first,
                messageState = item.second,
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
            tempFreelancerList = item.first
            _freelancerDataState.value = SearchContract.FreelancerDataState(
                list = item.first,
                messageState = item.second,
                message = if (item.second) R.string.errorMessage
                else R.string.empty
            )

        }
    }

    override fun onAction(action: SearchContract.UiAction) {
        when (action){
            SearchContract.UiAction.clickedAdvertsBttn -> onClickedAdvertsBttn()
            SearchContract.UiAction.clickedFreelancerBttn -> onClickedFreelancerBttn()
            is SearchContract.UiAction.searchViewOnQueryTextListener -> searchList(action.text)
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

    private fun searchList(text:String){
        // Detail is used for advert
        val searchAdvertList = tempAdvertList.filter { it.detail.lowercase().contains(text.lowercase()) }
        // Name Surname(title) is used for freelancer
        val searchFreelancerList = tempFreelancerList.filter { it.title.lowercase().contains(text.lowercase()) }
        _advertDataState.value = _advertDataState.value?.copy(
            list =  if (text.isEmpty()) tempAdvertList else searchAdvertList,
            messageState = searchAdvertList.isEmpty(),
            message = if(searchAdvertList.isEmpty()) R.string.notFoundAdvert else R.string.empty

        )

        _freelancerDataState.value = _freelancerDataState.value?.copy(
            list = if (text.isEmpty()) tempFreelancerList else searchFreelancerList,
            messageState = searchFreelancerList.isEmpty(),
            message =   if(searchFreelancerList.isEmpty()) R.string.notFoundFreelancer else R.string.empty

        )
    }
}