package com.example.homeawaytestapp.model.api.data.details

data class LikesItemTips(
    val count: Int,
    val groups: List<GroupLikesItemTips>,
    val summary: String
)