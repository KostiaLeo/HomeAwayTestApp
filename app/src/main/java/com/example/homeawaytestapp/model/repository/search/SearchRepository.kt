package com.example.homeawaytestapp.model.repository.search

import com.example.homeawaytestapp.model.api.data.search.VenueShort
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchVenues(query: String): Flow<Result<List<VenueShort>>>
}