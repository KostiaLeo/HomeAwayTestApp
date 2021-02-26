package com.example.homeawaytestapp.model.repository.details

import com.example.homeawaytestapp.model.api.VenuesApi
import com.example.homeawaytestapp.model.api.data.details.Venue
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val venuesApi: VenuesApi
): DetailsRepository {

    override suspend fun loadVenueDetails(id: String): Venue {
        return venuesApi.getVenueDetails(id).response.venue
    }
}