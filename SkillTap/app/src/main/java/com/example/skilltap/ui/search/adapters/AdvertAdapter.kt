package com.example.skilltap.ui.search.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.skilltap.R
import com.example.skilltap.databinding.AdvertAdapterDesignBinding

class AdvertAdapter(var mContext:Context)
    :RecyclerView.Adapter<AdvertAdapter.AdvertAdapterDesignKeeper>() {
        inner class AdvertAdapterDesignKeeper(var design:AdvertAdapterDesignBinding)
            :RecyclerView.ViewHolder(design.root){

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertAdapterDesignKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design: AdvertAdapterDesignBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.advert_adapter_design,
            parent,
            false
        )

        return  AdvertAdapterDesignKeeper(design)
    }

    override fun getItemCount(): Int {
        return  10
    }

    override fun onBindViewHolder(holder: AdvertAdapterDesignKeeper, position: Int) {
        holder.design.titleTxt.text = "Test Title"
    }
}