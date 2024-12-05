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
import com.example.skilltap.ui.home.adapters.TopOptionsAdapter
import com.example.skilltap.utils.toFragment

class HomeFragment : Fragment() {
    private lateinit var design : FragmentHomeBinding
    private lateinit var viewModel: HomeViewModelInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

        viewModel.uiState.observe(viewLifecycleOwner){
            design.uiState = it

        }

        design.searchView.setOnClickListener {
            val nav = HomeFragmentDirections.fromHomeToSearch()
            Navigation.toFragment(requireView(),nav)
        }

        design.topOptionsRvc.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val toOptionAdapter = TopOptionsAdapter(requireContext())
        design.topOptionsAdapter = toOptionAdapter


        design.categoryRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val categoryAdapter = CategoryAdapter(requireContext())
        design.categoryAdapter = categoryAdapter
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:HomeViewModelInterface by viewModels<HomeViewModel>()
        viewModel = tempViewModel
    }
}