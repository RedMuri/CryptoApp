package com.example.criptoapp.domain.use_cases

import com.example.criptoapp.domain.repositories.DatabaseRepository

class GetCoinInfoFromDbUseCase(private val repository: DatabaseRepository) {

    operator fun invoke(coinName: String) = repository.getCoinInfoByName(coinName)
}