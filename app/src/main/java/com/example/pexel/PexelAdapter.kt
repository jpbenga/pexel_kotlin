package com.example.pexel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter

class PexelAdapter(var listePhotos: MutableList<Photos>, var informationCallback: InformationCallback): Adapter<PexelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PexelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.liste_image, parent, false)
        return PexelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listePhotos.size
    }

    override fun onBindViewHolder(holder: PexelViewHolder, position: Int) {
        holder.bind(listePhotos[position], informationCallback)
    }
}