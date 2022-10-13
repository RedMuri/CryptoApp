package com.example.criptoapp.domain.use_cases

import com.example.criptoapp.domain.repositories.DatabaseRepository

class GetCoinListFromDbUseCase(private val repository: DatabaseRepository) {

    operator fun invoke() = repository.getCoinList()
}