package com.example.homeawaytestapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeawaytestapp.databinding.PhotosItemBinding
import com.example.homeawaytestapp.model.api.data.details.DetailPhoto
import com.example.homeawaytestapp.utils.load
import com.example.homeawaytestapp.utils.loadWithProgressBar

class VenuePhotosAdapter :
    ListAdapter<DetailPhoto, VenueDetailPhotoViewHolder>(DetailPhoto.DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueDetailPhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return VenueDetailPhotoViewHolder(PhotosItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: VenueDetailPhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class VenueDetailPhotoViewHolder(
    private val photosItemBinding: PhotosItemBinding
) : RecyclerView.ViewHolder(photosItemBinding.root) {

    fun bind(photo: DetailPhoto) {
        val link = buildString {
            append(photo.prefix)
            append(photo.width)
            append("x")
            append(photo.height)
            append(photo.suffix)
        }
        photosItemBinding.photo.loadWithProgressBar(link)
    }
}