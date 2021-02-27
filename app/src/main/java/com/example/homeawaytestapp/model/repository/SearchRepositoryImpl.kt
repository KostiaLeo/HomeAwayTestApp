package com.example.homeawaytestapp.model.repository

import com.example.homeawaytestapp.model.api.VenuesApi
import com.example.homeawaytestapp.model.api.data.VenueShort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val venuesApi: VenuesApi
) : SearchRepository {

    override fun searchVenues(query: String): Flow<Result<List<VenueShort>>> {
        return flow {
            val venues = venuesApi.searchVenues(query).response.venues
                .sortedBy {
                    it.location.distance
                }
            emit(Result.success(venues))
        }.catch { error ->
            emit(Result.failure(error))
        }
    }
}