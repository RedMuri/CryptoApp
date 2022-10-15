package com.example.criptoapp.domain.entities

data class CoinEntity(
    val firstName: String,
    val lastName: String?,
    val price: Float?,
    val lastUpdate: String?,
    val max: Float?,
    val min: Float?,
    val lastMarket: String?,
    val logoUrl: String?
)
