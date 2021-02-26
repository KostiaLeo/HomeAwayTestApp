package com.example.homeawaytestapp.model.api.data

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class VenueShort(
    val categories: List<Category>,
    val delivery: Delivery,
    val hasPerk: Boolean,
    val id: String,
    val location: Location,
    val name: String,
    val referralId: String,
    val venuePage: VenuePage
) {
    class DiffUtilCallback : DiffUtil.ItemCallback<VenueShort>() {
        override fun areItemsTheSame(oldItem: VenueShort, newItem: VenueShort): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VenueShort, newItem: VenueShort): Boolean {
            return oldItem == newItem
        }
    }
}