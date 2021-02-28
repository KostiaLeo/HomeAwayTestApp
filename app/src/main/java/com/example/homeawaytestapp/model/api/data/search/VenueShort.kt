package com.example.homeawaytestapp.model.api.data.search

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class VenueShort(
    val categories: List<Category>,
    val delivery: Delivery,
    val hasPerk: Boolean,
    val id: String,
    val location: Location,
    val name: String,
    val referralId: String,
    val venuePage: VenuePage
) : Serializable {

    class DiffUtilCallback : DiffUtil.ItemCallback<VenueShort>() {
        override fun areItemsTheSame(oldItem: VenueShort, newItem: VenueShort): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VenueShort, newItem: VenueShort): Boolean {
            return oldItem == newItem
        }
    }
}