package com.example.skilltap.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.skilltap.R
import com.example.skilltap.databinding.FragmentHomeBinding
import com.example.skilltap.ui.home.adapters.CategoryAdapter
import com.example.skilltap.utils.toFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var design : FragmentHomeBinding
    private lateinit var viewModel: HomeViewModelInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        design.categoryRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        viewModel.uiState.observe(viewLifecycleOwner){
            design.uiState = it

            val categoryAdapter = CategoryAdapter(requireContext(),it.categoryList)
            design.categoryAdapter = categoryAdapter

        }

        design.searchView.setOnClickListener {
            val nav = HomeFragmentDirections.fromHomeToSearch()
            Navigation.toFragment(requireView(),nav)

        }

        design.messageIcon.setOnClickListener {
            val nav = HomeFragmentDirections.toLastMessageList()
            Navigation.toFragment(requireView(),nav)
        }


        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:HomeViewModelInterface by viewModels<HomeViewModel>()
        viewModel = tempViewModel
    }
}