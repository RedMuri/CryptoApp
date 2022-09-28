package com.example.criptoapp.data.entities

import com.google.gson.annotations.SerializedName

data class Raw(
    @SerializedName("USD")
    val coin: Coin
)
