package com.example.skilltap.advertDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.skilltap.CoroutineTestRule
import com.example.skilltap.getOrAwaitValue
import com.example.skilltap.home.MockHomeService
import com.example.skilltap.search.MockSearchService
import com.example.skilltap.ui.advertDetail.AdvertDetailViewModel
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
import junit.framework.TestCase.assertNull


@RunWith(MockitoJUnitRunner::class)
class AdvertDetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel : AdvertDetailViewModel
    private lateinit var service:MockAdvertDetailService

    @get:Rule
    var coroutineRule = CoroutineTestRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        service = MockAdvertDetailService()

    }


    @After
    fun tearDown() {
    }


    @Test
    fun `when open advert detail screen,return uiState `() = runTest {
        viewModel = AdvertDetailViewModel(service)
        val uiState = viewModel.uiState.getOrAwaitValue()

        assertEquals(
            "packageIncludedTitle is not correct",
            R.string.packageIncludedTitle,
            uiState.packageIncludedTitle)

        assertEquals(
            "advertDescTitle is not correct",
            R.string.desc,
            uiState.advertDescTitle)

        assertEquals(
            "firstOptionTitle is not correct",
            R.string.basis,
            uiState.firstOptionTitle)


        assertEquals(
            "secondOptionTitle is not correct",
            R.string.standard,
            uiState.secondOptionTitle)

        assertEquals(
            "thirdOptionTitle is not correct",
            R.string.pro,
            uiState.thirdOptionTitle)


    }



    @Test
    fun `when open advert detail screen,return error `() = runTest {
        service.error = true
        viewModel = AdvertDetailViewModel(service)
        val advertState = viewModel.advertDetailState.getOrAwaitValue()



        assertEquals(
            "advertDetail is not null",
            null,
            advertState.advertDetail)

        assertEquals(
            "errorState is not true",
            true,
            advertState.errorState)


    }


}