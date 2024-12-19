package com.example.skilltap.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.skilltap.CoroutineTestRule
import com.example.skilltap.R
import com.example.skilltap.getOrAwaitValue
import com.example.skilltap.ui.home.Category
import com.example.skilltap.ui.home.HomeContract
import com.example.skilltap.ui.home.HomeServiceInterface
import com.example.skilltap.ui.home.HomeViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var service: MockHomeService

    @get:Rule
    var coroutineRule = CoroutineTestRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        service = MockHomeService()

    }



    @After
    fun tearDown() {
    }

    @Test
    fun `when open home screen ,return uiState not error `() = runTest {

        viewModel = HomeViewModel(service)


        val uiState = viewModel.uiState.getOrAwaitValue()

        // Test assertion
        assertEquals(
            "The title is not correct. ",
            R.string.hi,
            uiState.title)

        assertEquals(
            "The subtitle is not correct",
            R.string.homeSubTittle,
            uiState.subTitle)

        assertEquals(
            "The searchbarPlaceHolder is not correct",
            R.string.searchPlaceholder,
            uiState.searchbarPlaceHolder)

        assertEquals(
            "The categoryTitle is not correct Expected: Categories",
            R.string.categories,
            uiState.categoryTitle)


        assertFalse(uiState.errorState)
        assertEquals("The text of errorMessageTxt is not correct Expected: ''(empty) ",
            R.string.empty,
            uiState.errorMessage)

        assertFalse(uiState.navigationState)
        assertEquals("The navTitle is not correct Expected: ''(empty) ",
            R.string.homeNav,
            uiState.navTitle)


    }

    @Test
    fun `when open home screen ,return uiState  error `() = runTest {

        service.categoryError = true


        viewModel = HomeViewModel(service)

        val uiState = viewModel.uiState.getOrAwaitValue()

        // Test assertion
        assertEquals(
            "The title is not correct. ",
            R.string.hi,
            uiState.title)

        assertEquals(
            "The subtitle is not correct",
            R.string.homeSubTittle,
            uiState.subTitle)

        assertEquals(
            "The searchbarPlaceHolder is not correct",
            R.string.searchPlaceholder,
            uiState.searchbarPlaceHolder)

        assertEquals(
            "The categoryTitle is not correct.  Expected: Categories",
            R.string.categories,
            uiState.categoryTitle)

        assertTrue(uiState.errorState)
        assertEquals(
            "The text of  errorMessageTxt is not correct.  Expected: Something went wrong",
            R.string.errorMessage,
            uiState.errorMessage)

    }

}
