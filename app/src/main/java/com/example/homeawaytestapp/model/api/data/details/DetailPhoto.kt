package com.example.homeawaytestapp.model.api.data.details

import androidx.recyclerview.widget.DiffUtil

data class DetailPhoto(
    val createdAt: Int,
    val height: Int,
    val id: String,
    val prefix: String,
    val source: SourceDetailPhoto,
    val suffix: String,
    val visibility: String,
    val width: Int
) {
    class DiffUtilCallback : DiffUtil.ItemCallback<DetailPhoto>() {
        override fun areItemsTheSame(oldItem: DetailPhoto, newItem: DetailPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DetailPhoto, newItem: DetailPhoto): Boolean {
            return oldItem == newItem
        }
    }
}