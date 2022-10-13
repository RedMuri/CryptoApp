package com.example.criptoapp.domain.repositories

import com.example.criptoapp.domain.entities.CoinEntity

interface DatabaseRepository {

    fun getCoinList(): List<CoinEntity>
    fun getCoinInfoByName(coinName: String): CoinEntity
    fun insertCoins(coins: List<CoinEntity>)
}