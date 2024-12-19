package com.example.skilltap.ui.message

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.skilltap.R
import com.example.skilltap.databinding.BubbleMessageDesinBinding


class MessageAdapter(
    var mContext: Context,
    var viewModel:MessageViewModelInterface,
    var list:List<MessageData>
) : RecyclerView.Adapter<MessageAdapter.BubbleMessageDesignKeeper>() {
    inner  class BubbleMessageDesignKeeper(var design:BubbleMessageDesinBinding)
        :RecyclerView.ViewHolder(design.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BubbleMessageDesignKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design: BubbleMessageDesinBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.bubble_message_desin,
            parent,
            false
        )

        return  BubbleMessageDesignKeeper(design)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BubbleMessageDesignKeeper, position: Int) {
        viewModel.onBindViewHolder(position)
        holder.design.senderState = viewModel.senderMessageState.value
        holder.design.receiverState = viewModel.receiverState.value
    }
}