package com.example.pexel

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PexelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private var image: AppCompatImageView
    private var photographerName: AppCompatTextView

    init{
        image = itemView.findViewById(R.id.photo_pexel)
        photographerName = itemView.findViewById(R.id.photograph_name)
    }

    fun bind(
        photo: Photos
    ){
        Glide.with(image.context)
            .load(photo.src?.original)
            .into(image)
        photographerName.text = photo.photographer
    }
}