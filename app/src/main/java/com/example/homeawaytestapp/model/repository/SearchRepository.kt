package com.example.homeawaytestapp.model.repository

import com.example.homeawaytestapp.model.api.data.Venue
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchVenues(query: String): Flow<Result<List<Venue>>>
}