package com.example.homeawaytestapp.utils

import java.text.SimpleDateFormat
import java.util.*

typealias AndroidLocation = android.location.Location

val apiVersion: String
    get() {
        val format = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        return format.format(Date())
    }
