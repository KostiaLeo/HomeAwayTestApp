package com.example.homeawaytestapp.utils

import com.example.homeawaytestapp.model.api.data.search.VenueShortLocation
import com.google.android.gms.maps.model.LatLng

object LocationMapper {
    fun mapVenueLocationToLatLng(location: VenueShortLocation): LatLng {
        return LatLng(location.lat, location.lng)
    }
}