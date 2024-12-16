package com.example.skilltap.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.skilltap.CoroutineTestRule
import com.example.skilltap.getOrAwaitValue
import com.example.skilltap.home.MockHomeService
import com.example.skilltap.ui.search.SearchViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import com.example.skilltap.R
import com.example.skilltap.ui.search.SearchContract
import com.example.skilltap.ui.search.models.Advert
import com.example.skilltap.ui.search.models.Freelancer
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertNotEquals

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {
    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

   private  lateinit var viewModel:SearchViewModel
   private  lateinit var service:MockSearchService

   @get:Rule
    var coroutineRule = CoroutineTestRule()


    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        service = MockSearchService()

    }


    @After
    fun tearDown() {
    }

    @Test
    fun `when open search screen ,uiState will be return searchbarPlaceHolder `() = runTest {
        viewModel = SearchViewModel(service)
        val state = viewModel.uiState.getOrAwaitValue()

       assertEquals("The searchPlaceholder is not correct",
           R.string.searchPlaceholder,state.searchbarPlaceHolder)
    }

    @Test
    fun `when open search screen ,return AdvertButtonState and FreelancerButtonState `() = runTest {
        viewModel = SearchViewModel(service)
        val advertButtonState = viewModel.advertsButtonsState.getOrAwaitValue()
        val freelancerButtonState = viewModel.freelancerButtonState.getOrAwaitValue()

        // AdvertButtonState
        assertTrue(advertButtonState.selected)
        assertEquals("advertButtonStateText is not correct",
            R.string.adverts,advertButtonState.text)

        // FreelancerButtonState
        assertFalse(freelancerButtonState.selected)
        assertEquals("freelancerButtonStateText is not correct",
            R.string.freelancers,freelancerButtonState.text)

    }


    @Test
    fun `when clickedFreelancerBttn ,return AdvertButtonState and FreelancerButtonState `() = runTest {
        viewModel = SearchViewModel(service)
        viewModel.onAction(SearchContract.UiAction.clickedFreelancerBttn)

        val advertButtonState = viewModel.advertsButtonsState.getOrAwaitValue()
        val freelancerButtonState = viewModel.freelancerButtonState.getOrAwaitValue()

        // AdvertButtonState
        assertFalse(advertButtonState.selected)
        assertEquals("advertButtonStateText is not correct",
            R.string.adverts,advertButtonState.text)

        // FreelancerButtonState
        assertTrue(freelancerButtonState.selected)
        assertEquals("freelancerButtonStateText is not correct",
            R.string.freelancers,freelancerButtonState.text)
    }



    @Test
    fun `when onClickedAdvertsBttn ,return AdvertButtonState and FreelancerButtonState `() = runTest {
        viewModel = SearchViewModel(service)
        viewModel.onAction(SearchContract.UiAction.clickedAdvertsBttn)
        val advertButtonState = viewModel.advertsButtonsState.getOrAwaitValue()
        val freelancerButtonState = viewModel.freelancerButtonState.getOrAwaitValue()

        // AdvertButtonState
        assertTrue(advertButtonState.selected)
        assertEquals("advertButtonStateText is not correct",
            R.string.adverts,advertButtonState.text)

        // FreelancerButtonState
        assertFalse(freelancerButtonState.selected)
        assertEquals("freelancerButtonStateText is not correct",
            R.string.freelancers,freelancerButtonState.text)

    }

    @Test
    fun `When advertList gives an error  ,return message(Something went wrong) `() = runTest {
        service.advertError = true

        viewModel = SearchViewModel(service)

        val advertDataState = viewModel.advertDataState.getOrAwaitValue()

        assertTrue(advertDataState.messageState)
        assertEquals("Message is correct",R.string.errorMessage,advertDataState.message)
        assertEquals("list is not empty", emptyList<Advert>(),advertDataState.list)

    }


    @Test
    fun `When freelancer gives an error  ,return message(Something went wrong) `() = runTest {
        service.freelancerError = true

        viewModel = SearchViewModel(service)

        val freelancerDataState = viewModel.freelancerDataState.getOrAwaitValue()

        assertTrue(freelancerDataState.messageState)
        assertEquals("Message is correct",R.string.errorMessage,freelancerDataState.message)
        assertEquals("list is not empty", emptyList<Advert>(),freelancerDataState.list)

    }

    @Test
    fun `when advert search result is empty,return message(Advert not found)`() = runTest {
        viewModel = SearchViewModel(service)
        viewModel.onAction(SearchContract.UiAction.searchViewOnQueryTextListener("design"))

        val advertDataState = viewModel.advertDataState.getOrAwaitValue()

        assertTrue(advertDataState.messageState)
        assertEquals("Message is correct",R.string.notFoundAdvert,advertDataState.message)
        assertEquals("list is not empty", emptyList<Advert>(),advertDataState.list)
    }


    @Test
    fun `when advert search result is not empty,return message(empty)`() = runTest {
        service.advertError = false
        viewModel = SearchViewModel(service)

        viewModel.onAction(SearchContract.UiAction.searchViewOnQueryTextListener("developer"))

        val advertDataState = viewModel.advertDataState.getOrAwaitValue()

        assertFalse(advertDataState.messageState)
        assertEquals("Message is correct",R.string.empty,advertDataState.message)
        assertNotEquals("list is empty", emptyList<Advert>(),advertDataState.list)
    }


    @Test
    fun `when freelancer search result is empty,return message(Freelancer not found)`() = runTest {
        viewModel = SearchViewModel(service)
        viewModel.onAction(SearchContract.UiAction.searchViewOnQueryTextListener("Mary"))

        val freelancerDataState = viewModel.freelancerDataState.getOrAwaitValue()

        assertTrue(freelancerDataState.messageState)
        assertEquals("Message is correct",R.string.notFoundFreelancer,freelancerDataState.message)
        assertEquals("list is not empty", emptyList<Freelancer>(),freelancerDataState.list)
    }



    @Test
    fun `when freelancer search result is not empty,return message(empty)`() = runTest {
        service.advertError = false
        viewModel = SearchViewModel(service)

        viewModel.onAction(SearchContract.UiAction.searchViewOnQueryTextListener("mark"))

        val freelancerDataState = viewModel.freelancerDataState.getOrAwaitValue()

        assertFalse(freelancerDataState.messageState)
        assertEquals("Message is correct",R.string.empty,freelancerDataState.message)
        assertNotEquals("list is empty", emptyList<Freelancer>(),freelancerDataState.list)
    }












}