package com.example.homeawaytestapp.model.api.data.details

data class UserPage(
    val bio: String,
    val countryCode: String,
    val firstName: String,
    val lists: Lists,
    val tips: Tips,
    val type: String
)