package com.example.homeawaytestapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeawaytestapp.databinding.VenueSearchItemBinding
import com.example.homeawaytestapp.model.api.data.VenueShort
import com.example.homeawaytestapp.utils.load
import com.example.homeawaytestapp.utils.toKmOrM

class VenuesSearchAdapter(
    private val onVenueClicked: (VenueShort) -> Unit
) : ListAdapter<VenueShort, VenuesSearchViewHolder>(VenueShort.DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenuesSearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return VenuesSearchViewHolder(VenueSearchItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: VenuesSearchViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            bind(item)
            itemView.setOnClickListener {
                onVenueClicked(item)
            }
        }
    }
}

class VenuesSearchViewHolder(
    private val binding: VenueSearchItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(venue: VenueShort) {
        binding.venueName.text = venue.name

        val distance = venue.location.distance.toKmOrM(binding.root.context)
        binding.venueDistance.text = distance

        venue.categories.firstOrNull()?.let { category ->
            binding.category.text = category.name

            val imageSrc = buildString {
                append(category.icon.prefix)
                append("bg_64")
                append(category.icon.suffix)
            }
            binding.venueImage.load(imageSrc)
        }
    }
}