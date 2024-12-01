package com.example.skilltap.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.skilltap.R
import com.example.skilltap.databinding.TopOptionDesignBinding

class TopOptionsAdapter(
    var mContext: Context
) : RecyclerView.Adapter<TopOptionsAdapter.TopOptionsDesignKeeper>() {
    inner class TopOptionsDesignKeeper(var design:TopOptionDesignBinding)
        :RecyclerView.ViewHolder(design.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopOptionsDesignKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design:TopOptionDesignBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.top_option_design,
            parent,
            false
        )

        return  TopOptionsDesignKeeper(design)
    }

    override fun getItemCount(): Int {
       return  3
    }

    override fun onBindViewHolder(holder: TopOptionsDesignKeeper, position: Int) {
        holder.design.topOptionTxt.text = "test"
    }

}