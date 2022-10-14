package com.example.criptoapp.domain.repositories

import androidx.lifecycle.LiveData
import com.example.criptoapp.domain.entities.CoinEntity

interface DatabaseRepository {

    fun getCoinList(): LiveData<List<CoinEntity>>
    suspend fun getCoinInfoByName(coinName: String): CoinEntity
    suspend fun insertCoins(coins: List<CoinEntity>)
}