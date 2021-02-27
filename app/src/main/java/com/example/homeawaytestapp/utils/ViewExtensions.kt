package com.example.homeawaytestapp.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.homeawaytestapp.R

fun ImageView.loadWithProgressBar(src: String) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide.with(context)
        .load(src)
        .apply(RequestOptions()
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_restraunt_placeholder)
        )
        .into(this)
}

fun ImageView.load(src: String) {

    Glide.with(context)
        .load(src)
        .apply(RequestOptions()
            .placeholder(R.drawable.ic_restraunt_placeholder)
            .error(R.drawable.ic_restraunt_placeholder)
        )
        .into(this)
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}