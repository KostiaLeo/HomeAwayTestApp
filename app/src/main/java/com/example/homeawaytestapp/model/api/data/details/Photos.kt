package com.example.homeawaytestapp.model.api.data.details

import com.google.gson.annotations.SerializedName

data class Photos(
    val count: Int,
    @SerializedName("groups") val detailPhotoGroups: List<DetailPhotoGroup>
)