package com.example.skilltap.ui.subCategory

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.skilltap.R
import com.example.skilltap.databinding.CategoryDesignBinding
import com.example.skilltap.databinding.SubCategoryDesignBinding

class SubCategoryAdapter(
    var mContext: Context,
) : RecyclerView.Adapter<SubCategoryAdapter.SubCategoryDesignKeeper>() {
    inner  class  SubCategoryDesignKeeper(var design:SubCategoryDesignBinding)
        :RecyclerView.ViewHolder(design.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryDesignKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design: SubCategoryDesignBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.sub_category_design,
            parent,
            false
        )

        return  SubCategoryDesignKeeper(design)
    }

    override fun getItemCount(): Int {
        return  10
    }

    override fun onBindViewHolder(holder: SubCategoryDesignKeeper, position: Int) {
        holder.design.subCategoryTxt.text = "sub category"
    }
}