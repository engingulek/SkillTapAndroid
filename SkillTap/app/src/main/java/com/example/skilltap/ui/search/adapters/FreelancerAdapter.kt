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
import com.example.skilltap.ui.search.models.Freelancer
import com.example.skilltap.utils.PicassoImage
import com.example.skilltap.utils.toFragment

class FreelancerAdapter(
    var mContext: Context,
    var list : List<Freelancer>
    )
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
        return  list.size
    }

    override fun onBindViewHolder(holder: FreelancerAdapterKeeper, position: Int) {
        val freelancer = list[position]
        PicassoImage.covertToPicasso(freelancer.imageURL,holder.design.shapeableImageView)
        holder.design.nameSurnmaTxt.text = freelancer.title
        holder.design.workTxt.text = freelancer.subTitle
        holder.design.descTxt.text = freelancer.detail
        holder.design.ratingTxt.text = "${freelancer.rating}"
        holder.design.freelancerCardView.setOnClickListener {
            val nav = SearchFragmentDirections.toFreelancerDetail(freelancer.id)
            Navigation.toFragment(it,nav)
        }
    }
}