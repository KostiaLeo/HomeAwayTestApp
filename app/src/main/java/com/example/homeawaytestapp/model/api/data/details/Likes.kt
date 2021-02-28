package com.example.homeawaytestapp.model.api.data.details

data class Likes(
    val count: Int,
    val groups: List<GroupLikes>,
    val summary: String
)