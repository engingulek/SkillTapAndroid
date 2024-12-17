package com.example.skilltap.ui.search.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.skilltap.R
import com.example.skilltap.databinding.AdvertAdapterDesignBinding
import com.example.skilltap.ui.search.SearchFragmentDirections
import com.example.skilltap.ui.search.models.Advert
import com.example.skilltap.utils.PicassoImage
import com.example.skilltap.utils.toFragment

class AdvertAdapter(
    var mContext:Context,
    var list: List<Advert>
)
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
        return  list.size
    }

    override fun onBindViewHolder(holder: AdvertAdapterDesignKeeper, position: Int) {
        val advert = list[position]
        PicassoImage.covertToPicasso(advert.imageURL,holder.design.advertImage)
        holder.design.titleTxt.text = advert.title
        holder.design.priceTxt.text = " Start ${advert.price} Tl"
        holder.design.detailTxt.text = advert.detail
        holder.design.advertCardView.setOnClickListener {
            val nav = SearchFragmentDirections.toAdvertDetail(advert.id)
            Navigation.toFragment(it,nav)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Composable
    fun realoadAdapter(){
        notifyDataSetChanged()
    }
}