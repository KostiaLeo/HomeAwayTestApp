package com.example.homeawaytestapp.model.api.data.details

data class DetailPhotoGroup(
    val count: Int,
    val items: List<DetailPhoto>,
    val name: String,
    val type: String
)