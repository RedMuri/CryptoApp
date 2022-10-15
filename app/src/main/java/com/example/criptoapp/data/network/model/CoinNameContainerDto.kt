package com.example.criptoapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinNameContainerDto(
    @SerializedName("CoinInfo")
    val nameContainer: CoinNameDto? = null
)
