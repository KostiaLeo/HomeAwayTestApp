package com.example.homeawaytestapp.model.api.data.details

data class TimeframePopular(
    val days: String,
    val includesToday: Boolean,
    val `open`: List<OpenPopular>,
    val segments: List<Any>
)