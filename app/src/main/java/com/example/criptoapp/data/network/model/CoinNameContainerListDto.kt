package com.example.criptoapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinNameContainerListDto(
    @SerializedName("Data")
    val nameContainerList: List<CoinNameContainerDto>? = null
)
