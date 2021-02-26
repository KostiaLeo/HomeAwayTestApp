package com.example.homeawaytestapp.model.api.data.details

data class TimeframeX(
    val days: String,
    val includesToday: Boolean,
    val `open`: List<OpenX>,
    val segments: List<Any>
)