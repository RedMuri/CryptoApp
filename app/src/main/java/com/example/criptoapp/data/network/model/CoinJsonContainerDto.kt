package com.example.criptoapp.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinJsonContainerDto(
    @SerializedName("RAW")
    val json: JsonObject? = null
)
