package com.example.homeawaytestapp.model.api

import com.example.homeawaytestapp.model.api.data.details.VenueDetailsResponse
import com.example.homeawaytestapp.model.api.data.search.VenuesSearchResponse
import com.example.homeawaytestapp.utils.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VenuesApi {
    @GET("v2/venues/search")
    suspend fun searchVenues(
        @Query("query") query: String,
        @Query("client_id") clientId: String = CLIENT_ID,
        @Query("client_secret") clientSecret: String = CLIENT_SECRET,
        @Query("v") v: String = apiVersion,
        @Query("ll") ll: String = "$MAIN_LATITUDE,$MAIN_LONGITUDE",
        @Query("limit") limit: String = SEARCH_LIMIT
    ): VenuesSearchResponse

    @GET("v2/venues/{id}")
    suspend fun getVenueDetails(
        @Path("id") id: String,
        @Query("client_id") clientId: String = CLIENT_ID,
        @Query("client_secret") clientSecret: String = CLIENT_SECRET,
        @Query("v") v: String = apiVersion
    ): VenueDetailsResponse
}