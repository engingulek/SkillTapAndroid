package com.example.skilltap.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skilltap.R
import com.example.skilltap.databinding.FragmentHomeBinding

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
            viewModel.onAction(HomeContract.UiAction.clickedEstateType)
        }

        design.topOptionsRvc.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val adapter = TopOptionsAdapter(requireContext())
        design.topOptionsAdapter = adapter
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:HomeViewModelInterface by viewModels<HomeViewModel>()
        viewModel = tempViewModel
    }
}