package com.example.skilltap.ui.message

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skilltap.R
import com.example.skilltap.databinding.FragmentMessageBinding
import com.example.skilltap.ui.advertDetail.AdvertDetailViewModelInterface


class MessageFragment : Fragment() {
    private lateinit var  design : FragmentMessageBinding
    private lateinit var viewModel:MessageViewModelInterface
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_message, container, false)
        design.messageRv.layoutManager =  LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        viewModel.messageList.observe(viewLifecycleOwner){
            val messageAdapter = MessageAdapter(requireContext(),viewModel,it)
            design.messageAdapter = messageAdapter
        }
        return  design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : MessageViewModelInterface by viewModels<MessageViewModel>()
        viewModel = tempViewModel
    }


}