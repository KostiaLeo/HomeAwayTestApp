package com.example.homeawaytestapp.model.api

import com.example.homeawaytestapp.model.api.data.VenuesSearchResponse
import com.example.homeawaytestapp.model.api.data.details.VenueDetailsResponse
import com.example.homeawaytestapp.utils.CLIENT_ID
import com.example.homeawaytestapp.utils.CLIENT_SECRET
import com.example.homeawaytestapp.utils.DEFAULT_NEAR
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VenuesApi {
    @GET("v2/venues/search")
    suspend fun searchVenues(
        @Query("query") query: String,
        @Query("client_id") clientId: String = CLIENT_ID,
        @Query("client_secret") clientSecret: String = CLIENT_SECRET,
        @Query("v") v: String = "20210225",
        @Query("ll") ll:String = "47.6062,-122.3321",
        @Query("limit") limit: String = "20"
    ): VenuesSearchResponse

    @GET("v2/venues/{id}")
    suspend fun getVenueDetails(
        @Path("id") id: String,
        @Query("client_id") clientId: String = CLIENT_ID,
        @Query("client_secret") clientSecret: String = CLIENT_SECRET,
        @Query("v") v: String = "20210225"
    ): VenueDetailsResponse
}