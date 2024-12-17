package com.example.skilltap.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skilltap.R
import com.example.skilltap.databinding.FragmentSearchBinding
import com.example.skilltap.ui.home.HomeViewModel
import com.example.skilltap.ui.home.HomeViewModelInterface
import com.example.skilltap.ui.search.adapters.AdvertAdapter
import com.example.skilltap.ui.search.adapters.FreelancerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var design:FragmentSearchBinding
    private lateinit var viewModel: SearchViewModelInterface

    @SuppressLint("NotifyDataSetChanged")
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

        viewModel.advertDataState.observe(viewLifecycleOwner){
            val advertAdapter = AdvertAdapter(requireContext(),it.list)
            design.advertAdapter = advertAdapter
            design.advertDataState = it

            advertAdapter.notifyDataSetChanged()
        }

        viewModel.freelancerDataState.observe(viewLifecycleOwner){
            val freelancerAdapter = FreelancerAdapter(requireContext(),it.list)
            design.freelancerAdapter = freelancerAdapter
            design.freelancerDataState = it
            freelancerAdapter.notifyDataSetChanged()
        }

        design.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               return query?.let {

                   viewModel.onAction(SearchContract.UiAction.searchViewOnQueryTextListener(it))

                    true
                }?: false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return newText?.let {
                    viewModel.onAction(SearchContract.UiAction.searchViewOnQueryTextListener(it))
                    true
                }?: false
            }

        })





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