package com.example.homeawaytestapp.utils

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.homeawaytestapp.R

fun ImageView.load(src: String) {
    Log.d("VENUES", "source: $src")
    Glide.with(context)
        .load(src)
        .apply(RequestOptions()
            .placeholder(R.drawable.ic_restraunt_placeholder)
            .error(R.drawable.ic_restraunt_placeholder)
        )
        .into(this)
}