package com.example.homeawaytestapp.model.api.data.details

data class Group(
    val count: Int,
    val items: List<Item>,
    val name: String,
    val summary: String,
    val type: String
)