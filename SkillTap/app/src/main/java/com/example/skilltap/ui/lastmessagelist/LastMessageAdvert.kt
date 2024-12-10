package com.example.skilltap.ui.lastmessagelist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.skilltap.R

import com.example.skilltap.databinding.LastMessageDesignBinding

class LastMessageAdvert(var mContext: Context
) : RecyclerView.Adapter<LastMessageAdvert.LastMessageKeeper>() {

    inner class  LastMessageKeeper(var design: LastMessageDesignBinding)
        :RecyclerView.ViewHolder(design.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMessageKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design: LastMessageDesignBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.last_message_design,
            parent,
            false
        )

        return  LastMessageKeeper(design)
    }

    override fun getItemCount(): Int {
        return  10
    }

    override fun onBindViewHolder(holder: LastMessageKeeper, position: Int) {
        holder.design.lastMessageTxt.text = "last message"
    }
}