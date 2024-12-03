package com.example.skilltap.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skilltap.R
import com.example.skilltap.databinding.FragmentSearchBinding
import com.example.skilltap.ui.search.adapters.AdvertAdapter
import com.example.skilltap.ui.search.adapters.FreelancerAdapter

class SearchFragment : Fragment() {
    private lateinit var design:FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_search, container, false)
        design.listRv.layoutManager =  LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val advertAdapter = AdvertAdapter(requireContext())
        val freelancerAdapter = FreelancerAdapter(requireContext())
        design.advertAdapter = advertAdapter
        design.freelancerAdapter = freelancerAdapter
        return  design.root
    }
}