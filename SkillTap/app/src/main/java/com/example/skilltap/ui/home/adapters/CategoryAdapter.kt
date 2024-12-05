package com.example.skilltap.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.skilltap.R
import com.example.skilltap.databinding.CategoryDesignBinding
import com.example.skilltap.databinding.TopOptionDesignBinding

class CategoryAdapter(
    var mContext: Context
) : RecyclerView.Adapter<CategoryAdapter.CategoryDesignKeeper>()
{
    inner class CategoryDesignKeeper(var design:CategoryDesignBinding)
        :RecyclerView.ViewHolder(design.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDesignKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design: CategoryDesignBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.category_design,
            parent,
            false
        )

        return  CategoryDesignKeeper(design)
    }

    override fun getItemCount(): Int {
        return  4
    }

    override fun onBindViewHolder(holder: CategoryDesignKeeper, position: Int) {
        holder.design.categoryTitle.text = "Title"
        holder.design.categoryDeveloperSubTitle.text = "10.000 developer"
        holder.design.advertsTxt.text = "20.000 adverts"
    }
}