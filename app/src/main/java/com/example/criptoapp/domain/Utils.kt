package com.example.criptoapp.domain

import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        const val API_KEY = "0e5d65020b565c35d1c07ce1bff9ef3ebaf2d7cf5e3de951078fdc487c9f95bf"
        const val BASE_IMAGE_URL = "https://www.cryptocompare.com/"
        const val BASE_URL = "https://min-api.cryptocompare.com/data/"

        fun convertMls(mls: Long): String {
            val timestamp = Timestamp(mls)
            val date = Date(timestamp.time)
            val pattern = "HH:mm:ss"
            val sdf = SimpleDateFormat(pattern,Locale.getDefault())
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(date)
        }
    }
}