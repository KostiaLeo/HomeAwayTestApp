package com.example.homeawaytestapp.model.api.data

import com.google.android.gms.maps.model.LatLng

data class Location(
    val address: String,
    val cc: String,
    val city: String,
    val country: String,
    val crossStreet: String,
    val distance: Int,
    val formattedAddress: List<String>,
    val labeledLatLngs: List<LabeledLatLng>,
    val lat: Double,
    val lng: Double,
    val neighborhood: String,
    val postalCode: String,
    val state: String
) {
    fun mapToLatLng(): LatLng {
        return LatLng(lat, lng)
    }
}