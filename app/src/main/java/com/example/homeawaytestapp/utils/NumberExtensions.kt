package com.example.homeawaytestapp.utils

import android.content.Context
import com.example.homeawaytestapp.R
import java.text.DecimalFormat

fun Int.toKmOrM(context: Context): String {
    val (distance, units) = if (this > 1000) {
        val kms = this.toDouble() / 1000
        DecimalFormat("#.##").format(kms) to context.getString(R.string.km)
    } else {
        this.toString() to context.getString(R.string.m)
    }

    return "$distance $units"
}