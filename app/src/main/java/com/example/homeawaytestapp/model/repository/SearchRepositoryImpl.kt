package com.example.homeawaytestapp.model.repository

import com.example.homeawaytestapp.model.api.VenuesApi
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val venuesApi: VenuesApi
) : SearchRepository {
    override suspend fun getData(): String {
        return "SUCCESS"
    }
}