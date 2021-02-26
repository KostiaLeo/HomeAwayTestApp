package com.example.homeawaytestapp.model.api.data.details

data class Timeframe(
    val days: String,
    val includesToday: Boolean,
    val `open`: List<Open>,
    val segments: List<Any>
)