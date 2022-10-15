package com.example.criptoapp.domain.use_cases

import com.example.criptoapp.domain.entities.CoinEntity
import com.example.criptoapp.domain.repositories.DatabaseRepository

class InsertCoinsInDbUseCase(private val repository: DatabaseRepository) {

    suspend operator fun invoke(coins: List<CoinEntity>) = repository.insertCoins(coins)
}