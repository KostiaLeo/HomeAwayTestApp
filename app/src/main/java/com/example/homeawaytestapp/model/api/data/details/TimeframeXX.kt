package com.example.homeawaytestapp.model.api.data.details

data class TimeframeXX(
    val days: String,
    val includesToday: Boolean,
    val `open`: List<OpenXX>,
    val segments: List<Any>
)