package com.example.criptoapp.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.criptoapp.domain.Utils
import com.squareup.picasso.Picasso
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("lastUpdate")
fun bindLasUpdate(textView: TextView, mls: Long) {
    textView.text = convertMls(mls * 1000)
}

private fun convertMls(mls: Long): String {
    val timestamp = Timestamp(mls)
    val date = Date(timestamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}

@BindingAdapter("coinImage")
fun bindCoinImage(imageView: ImageView, logoUrl: String?) {
    if (logoUrl != null)
        Picasso.get().load(Utils.BASE_IMAGE_URL + logoUrl).into(imageView)
}


