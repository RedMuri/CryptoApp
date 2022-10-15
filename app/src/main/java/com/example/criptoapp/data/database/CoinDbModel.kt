package com.example.criptoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coins")
data class CoinDbModel(
    @PrimaryKey
    val firstName: String,
    val lastName: String?,
    val price: Float?,
    val lastUpdate: Long?,
    val max: Float?,
    val min: Float?,
    val lastMarket: String?,
    val logoUrl: String?
)
