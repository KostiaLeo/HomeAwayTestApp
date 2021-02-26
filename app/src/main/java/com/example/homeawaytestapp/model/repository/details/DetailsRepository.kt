package com.example.homeawaytestapp.model.repository.details

import com.example.homeawaytestapp.model.api.data.details.Venue

interface DetailsRepository {
    suspend fun loadVenueDetails(id: String): Venue
}