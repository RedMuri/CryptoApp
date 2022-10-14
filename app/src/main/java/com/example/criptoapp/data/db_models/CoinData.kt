package com.example.criptoapp.data.db_models

import com.google.gson.annotations.SerializedName

data class CoinData(
    @SerializedName("CoinInfo")
    val coinInfoName: CoinWithName? = null
)
