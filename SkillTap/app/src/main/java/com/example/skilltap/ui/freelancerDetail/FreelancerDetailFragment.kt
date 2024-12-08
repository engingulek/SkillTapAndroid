package com.example.skilltap.ui.freelancerDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skilltap.R
import com.example.skilltap.databinding.FragmentFreelancerDetailBinding


class FreelancerDetailFragment : Fragment() {
    private lateinit var  design : FragmentFreelancerDetailBinding
    private lateinit var viewModel:FreelancerDetailViewModelInterface


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_freelancer_detail, container, false)
        val ownerAdvertAdapter = OwnerAdvertAdapter(requireContext())
        design.ownerAdvertAdapter = ownerAdvertAdapter
        viewModel.uiState.observe(viewLifecycleOwner){
            design.uiState = it
        }
        design.ownerAdvertsRv.layoutManager =  LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : FreelancerDetailViewModelInterface by viewModels<FreelancerDetailViewModel>()
        viewModel = tempViewModel
    }

}