package com.example.homeawaytestapp.model.api.data.details

data class Popular(
    val isLocalHoliday: Boolean,
    val isOpen: Boolean,
    val timeframes: List<TimeframeXX>
)