package com.example.skilltap.ui.search.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.skilltap.R
import com.example.skilltap.databinding.AdvertAdapterDesignBinding
import com.example.skilltap.databinding.FreelancerAdapterDesingBinding
import com.example.skilltap.ui.search.SearchFragmentDirections
import com.example.skilltap.utils.toFragment

class FreelancerAdapter(var mContext: Context)
    : RecyclerView.Adapter<FreelancerAdapter.FreelancerAdapterKeeper>() {
        inner  class FreelancerAdapterKeeper(var design:FreelancerAdapterDesingBinding)
            :RecyclerView.ViewHolder(design.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreelancerAdapterKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design: FreelancerAdapterDesingBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.freelancer_adapter_desing,
            parent,
            false
        )

        return  FreelancerAdapterKeeper(design)
    }

    override fun getItemCount(): Int {
        return  10
    }

    override fun onBindViewHolder(holder: FreelancerAdapterKeeper, position: Int) {
        holder.design.nameSurnmaTxt.text = "Name Surname"
        holder.design.freelancerCardView.setOnClickListener {
            val nav = SearchFragmentDirections.toFreelancerDetail()
            Navigation.toFragment(it,nav)
        }
    }
}