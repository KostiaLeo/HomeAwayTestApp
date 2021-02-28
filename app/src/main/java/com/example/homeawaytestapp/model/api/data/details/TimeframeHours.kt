package com.example.homeawaytestapp.model.api.data.details

data class TimeframeHours(
    val days: String,
    val includesToday: Boolean,
    val `open`: List<OpenHours>,
    val segments: List<Any>
)