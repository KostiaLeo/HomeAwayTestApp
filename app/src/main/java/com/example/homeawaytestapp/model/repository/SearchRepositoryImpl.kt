package com.example.homeawaytestapp.model.repository

import com.example.homeawaytestapp.model.api.VenuesApi
import com.example.homeawaytestapp.model.api.data.Venue
import com.example.homeawaytestapp.model.api.data.VenuesSearchResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val venuesApi: VenuesApi
) : SearchRepository {

    override fun searchVenues(query: String): Flow<Result<List<Venue>>> {
        return flow {
            val venues = venuesApi.searchVenues(query).response.venues
            emit(Result.success(venues))
        }.catch { error ->
            emit(Result.failure(error))
        }
    }
}