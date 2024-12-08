package com.example.skilltap.ui.advertDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.skilltap.R
import com.example.skilltap.databinding.FragmentAdvertDetailBinding

class AdvertDetailFragment : Fragment() {
    private lateinit var design:FragmentAdvertDetailBinding
    private lateinit var viewModel:AdvertDetailViewModelInterface
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_advert_detail, container, false)
        viewModel.uiState.observe(viewLifecycleOwner){
            design.uiState = it
        }

        design.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            viewModel.onAction(AdvertDetailContract.UiAction.clicledRadionButton(checkedId))
        }

        return  design.root
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AdvertDetailViewModelInterface by viewModels<AdvertDetailViewModel>()
        viewModel = tempViewModel



    }



}