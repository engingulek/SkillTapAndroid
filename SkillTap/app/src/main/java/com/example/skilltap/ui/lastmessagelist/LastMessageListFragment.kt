package com.example.skilltap.ui.lastmessagelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skilltap.R
import com.example.skilltap.databinding.FragmentLastMessageListBinding


class LastMessageListFragment : Fragment() {
    private lateinit var  desing: FragmentLastMessageListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        desing = DataBindingUtil.inflate(inflater,R.layout.fragment_last_message_list, container, false)
        desing.lastMessageRv.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        val lastMessageAdapter = LastMessageAdvert(requireContext())
        desing.lastMessageAdapter = lastMessageAdapter
        return desing.root
    }


}