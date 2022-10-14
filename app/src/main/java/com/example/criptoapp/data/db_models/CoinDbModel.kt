package com.example.criptoapp.data.db_models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coins")
data class CoinDbModel(
    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    val firstName: String,
    @SerializedName("TOSYMBOL")
    val lastName: String,
    @SerializedName("PRICE")
    val price: Float,
    @SerializedName("LASTUPDATE")
    val lastUpdate: Long,
    @SerializedName("HIGHDAY")
    val max: Float,
    @SerializedName("LOWDAY")
    val min: Float,
    @SerializedName("LASTMARKET")
    val lastMarket: String,
    @SerializedName("IMAGEURL")
    val logoUrl: String
)
