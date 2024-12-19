package com.example.skilltap.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Color

import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.skilltap.R
import com.example.skilltap.databinding.CategoryDesignBinding
import com.example.skilltap.databinding.TopOptionDesignBinding
import com.example.skilltap.ui.home.Category
import com.example.skilltap.ui.home.HomeContract
import com.example.skilltap.ui.home.HomeFragmentDirections
import com.example.skilltap.ui.home.HomeViewModelInterface
import com.example.skilltap.utils.PicassoImage
import com.example.skilltap.utils.toFragment

class CategoryAdapter(
    var mContext: Context,
    var list:List<Category>
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
        return  list.size
    }

    override fun onBindViewHolder(holder: CategoryDesignKeeper, position: Int) {
        val category = list[position]
        holder.design.categoryTitle.text = category.title
        holder.design.freelancerCountTxt.text = "${category.freelancerCount} Freelancers"
        holder.design.advertsCountTxt.text = "${category.advertCount} Adverts"
        PicassoImage.covertToPicasso(category.imageUrl,holder.design.categoryImage)
        holder.design.categoryConstraintLayout.setBackgroundColor(Color.parseColor(category.colorCode))

    }
}