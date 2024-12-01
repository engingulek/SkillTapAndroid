package com.example.skilltap.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skilltap.R
import com.example.skilltap.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var design : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        design.topOptionsRvc.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val adapter = TopOptionsAdapter(requireContext())
        design.topOptionsAdapter = adapter
        return design.root
    }
}