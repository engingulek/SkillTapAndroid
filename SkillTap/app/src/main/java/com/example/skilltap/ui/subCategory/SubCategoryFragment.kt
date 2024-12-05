package com.example.skilltap.ui.subCategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skilltap.R
import com.example.skilltap.databinding.FragmentSubCategoryBinding

class SubCategoryFragment : Fragment() {
    private lateinit var design:FragmentSubCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_sub_category, container, false)
        design.subCategoryRv.layoutManager =  LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        val subCategoryAdapter = SubCategoryAdapter(requireContext())
        design.subCategoryAdapter = subCategoryAdapter
        return design.root
    }

}