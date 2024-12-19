package com.example.skilltap.ui.freelancerDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skilltap.R
import com.example.skilltap.databinding.FragmentFreelancerDetailBinding
import com.example.skilltap.utils.PicassoImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FreelancerDetailFragment : Fragment() {
    private lateinit var  design : FragmentFreelancerDetailBinding
    private lateinit var viewModel:FreelancerDetailViewModelInterface


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_freelancer_detail, container, false)
        val bundle : FreelancerDetailFragmentArgs  by navArgs()
        val id = bundle.id
        viewModel.getFreelancerDetail(id)

        viewModelActions()
        design.ownerAdvertsRv.layoutManager =  LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : FreelancerDetailViewModelInterface by viewModels<FreelancerDetailViewModel>()
        viewModel = tempViewModel
    }


    private  fun viewModelActions(){
        viewModel.uiState.observe(viewLifecycleOwner){
            design.uiState = it
            toolbarNavigation(it.navigationState,it.navTitle)


        }
        viewModel.freelancerDetailState.observe(viewLifecycleOwner){
            design.freelancerDetailState = it
            it.freelancerDetail?.let { detail ->
                val url = detail.imageURL
                PicassoImage.covertToPicasso(url,design.freelancerImage)
                val adverts = detail.adverts

                val ownerAdvertAdapter = OwnerAdvertAdapter(requireContext(),adverts)
                design.ownerAdvertAdapter = ownerAdvertAdapter

            }
        }
    }


    private  fun toolbarNavigation(state:Boolean,navTitle:Int){
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(state)
            title = getString(navTitle)
        }

        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {

            findNavController().popBackStack()
        }
    }


}