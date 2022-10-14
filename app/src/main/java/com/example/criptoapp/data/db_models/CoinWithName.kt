package com.example.criptoapp.data.db_models

import com.google.gson.annotations.SerializedName

data class CoinWithName(
    @SerializedName("Name")
    val name: String? = null
)
