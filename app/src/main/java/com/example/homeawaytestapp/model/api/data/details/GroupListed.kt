package com.example.homeawaytestapp.model.api.data.details

data class GroupListed(
    val count: Int,
    val items: List<ItemGroupListed>,
    val name: String,
    val type: String
)