package com.example.homeawaytestapp.model.api.data

data class Geocode(
    val feature: Feature,
    val parents: List<Any>,
    val what: String,
    val `where`: String
)