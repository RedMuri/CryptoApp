package com.example.criptoapp.domain.repositories

import com.example.criptoapp.domain.entities.CoinEntity

interface RetrofitRepository {

    suspend fun getCoinList(): List<CoinEntity>
}