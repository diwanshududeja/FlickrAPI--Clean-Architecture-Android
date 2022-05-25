package com.demo.flickr.ui.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.flickr.data.vo.PhotoDetail
import com.demo.flickr.databinding.ItemPhotoBinding

class PhotosViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindModel(photo: PhotoDetail){
        binding.textName.text = photo.title
        if(photo.url_m?.isNotEmpty() == true){
            Glide.with(itemView.context)
                .load(photo.url_m)
                .centerCrop()
                .into(binding.imagePhoto)
        }




    }
}