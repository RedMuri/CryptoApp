package com.example.criptoapp.data.entities

import com.google.gson.annotations.SerializedName

data class ResponseTop(
    @SerializedName("Data")
    val listOfCoins: List<CoinData>
)
