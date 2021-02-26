package com.example.homeawaytestapp.model.api.data

sealed class ResultState {
    object Loading
    class Success<T>(t: T)
    class Error(e: Throwable)
}