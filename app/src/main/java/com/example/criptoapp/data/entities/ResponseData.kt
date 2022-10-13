package com.example.criptoapp.data.entities

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("RAW")
    val raw: JsonObject? = null
)
