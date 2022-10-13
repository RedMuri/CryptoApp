package com.example.criptoapp.data.entities

import com.google.gson.annotations.SerializedName

data class CoinWithName(
    @SerializedName("Name")
    val name: String? = null
)
