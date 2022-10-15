package com.example.criptoapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinDto(
    @SerializedName("FROMSYMBOL")
    val firstName: String,
    @SerializedName("TOSYMBOL")
    val lastName: String?,
    @SerializedName("PRICE")
    val price: Float?,
    @SerializedName("LASTUPDATE")
    val lastUpdate: Long?,
    @SerializedName("HIGHDAY")
    val max: Float?,
    @SerializedName("LOWDAY")
    val min: Float?,
    @SerializedName("LASTMARKET")
    val lastMarket: String?,
    @SerializedName("IMAGEURL")
    val logoUrl: String?
)

