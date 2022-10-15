package com.example.criptoapp.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("lastUpdate")
fun bindLasUpdate(textView: TextView, lastUpdate: String?) {
    textView.text = lastUpdate?:""
}

@BindingAdapter("coinImage")
fun bindCoinImage(imageView: ImageView, logoUrl: String?) {
    if (logoUrl != null)
        Picasso.get().load(logoUrl).into(imageView)
}


