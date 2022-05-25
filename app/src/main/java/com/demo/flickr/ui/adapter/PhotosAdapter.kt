package com.demo.flickr.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.flickr.data.vo.PhotoDetail
import com.demo.flickr.databinding.ItemPhotoBinding
import com.demo.flickr.ui.viewholder.PhotosViewHolder

class PhotosAdapter:  RecyclerView.Adapter<PhotosViewHolder>() {

    var photosList: List<PhotoDetail> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bindModel(photosList[position])
    }

    override fun getItemCount(): Int {
        return photosList.size
    }
}