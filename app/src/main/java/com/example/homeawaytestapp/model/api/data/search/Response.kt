package com.example.homeawaytestapp.model.api.data.search

import com.google.gson.annotations.SerializedName

data class Response(
    val geocode: Geocode,
    @SerializedName("venues") val venues: List<VenueShort>
)