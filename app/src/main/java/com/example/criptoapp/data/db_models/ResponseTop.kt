package com.example.criptoapp.data.db_models

import com.google.gson.annotations.SerializedName

data class ResponseTop(
    @SerializedName("Data")
    val data: List<CoinData>? = null
)
