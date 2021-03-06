package com.example.homeawaytestapp.model.api.data.details

data class Hours(
    val dayData: List<Any>,
    val isLocalHoliday: Boolean,
    val isOpen: Boolean,
    val richStatus: RichStatusHours,
    val status: String,
    val timeframes: List<TimeframeHours>
)