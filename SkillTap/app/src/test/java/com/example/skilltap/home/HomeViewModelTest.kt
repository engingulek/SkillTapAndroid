package com.example.skilltap.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.skilltap.R
import com.example.skilltap.ui.home.HomeServiceInterface
import com.example.skilltap.ui.home.HomeViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private lateinit var viewModel: HomeViewModel
    private lateinit var service:MockHomeService

    @Before
    fun setup(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        service = MockHomeService()
        viewModel = HomeViewModel(service)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `when open home screen ,return uiState not error `() = runTest{
        val state = viewModel.uiState.value
        state?.let {
            assertEquals(it.title,R.string.hi)
            assertEquals(it.subTitle,R.string.homeSubTittle)
            assertEquals(it.searchbarPlaceHolder,R.string.searchPlaceholder)
            assertEquals(it.categoryTitle,R.string.categories)

        }
    }
}