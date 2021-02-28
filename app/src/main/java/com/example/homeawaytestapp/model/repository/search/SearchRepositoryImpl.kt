package com.example.homeawaytestapp.model.repository.search

import com.example.homeawaytestapp.model.api.VenuesApi
import com.example.homeawaytestapp.model.api.data.search.VenueShort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val venuesApi: VenuesApi
) : SearchRepository {

    override fun searchVenues(query: String): Flow<Result<List<VenueShort>>> {
        return flow {
            val result = runCatching { venuesApi.searchVenues(query).response.venues }
            emit(result)
        }
    }
}