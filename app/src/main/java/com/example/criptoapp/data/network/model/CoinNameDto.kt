package com.example.criptoapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinNameDto(
    @SerializedName("Name")
    val name: String? = null
)