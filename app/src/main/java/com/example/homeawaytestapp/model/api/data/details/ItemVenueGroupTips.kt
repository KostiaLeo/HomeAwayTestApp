package com.example.homeawaytestapp.model.api.data.details

data class ItemVenueGroupTips(
    val agreeCount: Int,
    val authorInteractionType: String,
    val canonicalUrl: String,
    val createdAt: Int,
    val disagreeCount: Int,
    val id: String,
    val lang: String,
    val likes: LikesItemTips,
    val logView: Boolean,
    val text: String,
    val todo: Todo,
    val type: String,
    val user: UserTips
)