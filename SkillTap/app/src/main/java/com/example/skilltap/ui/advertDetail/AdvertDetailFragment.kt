package com.example.skilltap.ui.advertDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.skilltap.R
import com.example.skilltap.databinding.FragmentAdvertDetailBinding
import com.example.skilltap.utils.PicassoImage
import com.example.skilltap.utils.toFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdvertDetailFragment : Fragment() {
    private lateinit var design:FragmentAdvertDetailBinding
    private lateinit var viewModel:AdvertDetailViewModelInterface
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_advert_detail, container, false)
        val bundle :  AdvertDetailFragmentArgs by navArgs()
        val id = bundle.id

        viewModel.getAdvertId(id)

        viewModel.uiState.observe(viewLifecycleOwner){
            design.uiState = it
        }
        viewModel.advertDetailState.observe(viewLifecycleOwner){
            design.advertDetailState = it
            //TODO: Review
            it.advertDetail?.let {advertDetail ->
                PicassoImage.covertToPicasso(advertDetail.image,design.baseImage)
                PicassoImage.covertToPicasso(advertDetail.freelancer.imageURL,design.advertOwnerImageView)
            }

        }


        viewModel.packageIncludeState.observe(viewLifecycleOwner){
            design.packageState = it
        }

        viewModel.getAdvertId(3);

        design.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.firstOptionId -> {
                    viewModel.onAction(AdvertDetailContract.UiAction.clicledRadionButton(0))
                }
                R.id.secondOptionId -> {
                    viewModel.onAction(AdvertDetailContract.UiAction.clicledRadionButton(1))
                }
                R.id.thirdOptionId-> {
                    viewModel.onAction(AdvertDetailContract.UiAction.clicledRadionButton(2))
                }
            }

        }

        design.sendMessageButton.setOnClickListener {
            val nav = AdvertDetailFragmentDirections.toMessage()
            Navigation.toFragment(it,nav)
        }

        return  design.root
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AdvertDetailViewModelInterface by viewModels<AdvertDetailViewModel>()
        viewModel = tempViewModel



    }



}