package com.example.homeawaytestapp.model.api.data

data class Feature(
    val cc: String,
    val displayName: String,
    val geometry: Geometry,
    val highlightedName: String,
    val id: String,
    val longId: String,
    val matchedName: String,
    val name: String,
    val slug: String,
    val woeType: Int
)