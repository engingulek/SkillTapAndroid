package com.example.skilltap.ui.freelancerDetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.skilltap.R
import com.example.skilltap.databinding.AdvertAdapterDesignBinding
import com.example.skilltap.ui.search.SearchFragmentDirections
import com.example.skilltap.utils.toFragment

class OwnerAdvertAdapter(var mContext: Context)
    :RecyclerView.Adapter<OwnerAdvertAdapter.OwnerAdapterDesignKeeper>(){
        inner class OwnerAdapterDesignKeeper(var design : AdvertAdapterDesignBinding)
            :RecyclerView.ViewHolder(design.root){

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnerAdapterDesignKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design: AdvertAdapterDesignBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.advert_adapter_design,
            parent,
            false
        )

        return  OwnerAdapterDesignKeeper(design)
    }

    override fun getItemCount(): Int {
        return  10
    }

    override fun onBindViewHolder(holder: OwnerAdapterDesignKeeper, position: Int) {
        holder.design.titleTxt.text = "Test Title"
        holder.design.advertCardView.setOnClickListener {
            val nav = SearchFragmentDirections.toAdvertDetail(3)
            Navigation.toFragment(it,nav)
        }
    }
}