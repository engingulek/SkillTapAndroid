package com.example.skilltap.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skilltap.R
import com.example.skilltap.databinding.FragmentSearchBinding
import com.example.skilltap.ui.home.HomeViewModel
import com.example.skilltap.ui.home.HomeViewModelInterface
import com.example.skilltap.ui.search.adapters.AdvertAdapter
import com.example.skilltap.ui.search.adapters.FreelancerAdapter

class SearchFragment : Fragment() {
    private lateinit var design:FragmentSearchBinding
    private lateinit var viewModel: SearchViewModelInterface
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search,
            container,
            false)

        onCreateViewActions()

        buttonsActions()


        design.listRv.layoutManager =  LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        val freelancerAdapter = FreelancerAdapter(requireContext())
        val advertAdapter = AdvertAdapter(requireContext())
        design.advertAdapter = advertAdapter
        design.freelancerAdapter = freelancerAdapter

        return  design.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SearchViewModelInterface by viewModels<SearchViewModel>()
        viewModel = tempViewModel
    }

    private fun buttonsActions(){
        design.advertsBttn.setOnClickListener {
            viewModel.onAction(SearchContract.UiAction.clickedAdvertsBttn)
        }

        design.freelancerBttn.setOnClickListener {
            viewModel.onAction(SearchContract.UiAction.clickedFreelancerBttn)
        }
    }

    private fun onCreateViewActions(){
        viewModel.uiState.observe(viewLifecycleOwner){
            design.uiState = it
        }

        viewModel.advertsButtonsState.observe(viewLifecycleOwner){
            design.advertButtonState = it
        }

        viewModel.freelancerButtonState.observe(viewLifecycleOwner){
            design.freelancerButtonState = it
        }

    }



}