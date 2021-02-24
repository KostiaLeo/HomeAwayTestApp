package com.example.homeawaytestapp.model.repository

interface SearchRepository {
    suspend fun getData(): String
}