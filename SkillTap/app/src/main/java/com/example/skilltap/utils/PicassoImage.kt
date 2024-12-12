package com.example.skilltap.utils

import android.widget.ImageView
import com.example.skilltap.R
import com.squareup.picasso.Picasso


class PicassoImage {
    companion object {
        fun covertToPicasso(url:String,image: ImageView) {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(image)
        }
    }
}