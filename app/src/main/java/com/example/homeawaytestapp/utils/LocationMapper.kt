package com.example.homeawaytestapp.utils

import com.example.homeawaytestapp.model.api.data.search.Location
import com.google.android.gms.maps.model.LatLng

object LocationMapper {
    fun mapVenueLocationToLatLng(location: Location): LatLng {
        return LatLng(location.lat, location.lng)
    }
}