package com.example.homeawaytestapp.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.homeawaytestapp.R
import java.text.DecimalFormat

typealias AndroidLocation = android.location.Location

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus ?: View(this)
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Int.toKmOrM(context: Context): String {
    val (distance, units) = if (this > 1000) {
        val kms = this.toDouble() / 1000
        DecimalFormat("#.##").format(kms) to context.getString(R.string.km)
    } else {
        this.toString() to context.getString(R.string.m)
    }

    return "$distance $units"
}