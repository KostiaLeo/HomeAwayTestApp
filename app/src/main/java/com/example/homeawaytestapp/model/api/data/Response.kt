package com.example.homeawaytestapp.model.api.data

data class Response(
    val geocode: Geocode,
    val venues: List<Venue>
)