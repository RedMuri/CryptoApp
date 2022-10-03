package com.example.criptoapp.data.entities

import com.google.gson.annotations.SerializedName

data class CoinData(
    @SerializedName("CoinInfo")
    val coinInfoName: CoinWithName? = null
)
