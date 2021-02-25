package com.example.homeawaytestapp.model.api.data

import androidx.recyclerview.widget.DiffUtil

data class Venue(
    val categories: List<Category>,
    val delivery: Delivery,
    val hasPerk: Boolean,
    val id: String,
    val location: Location,
    val name: String,
    val referralId: String,
    val venuePage: VenuePage
) {
    class DiffUtilCallback : DiffUtil.ItemCallback<Venue>() {
        override fun areItemsTheSame(oldItem: Venue, newItem: Venue): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Venue, newItem: Venue): Boolean {
            return oldItem == newItem
        }
    }
}