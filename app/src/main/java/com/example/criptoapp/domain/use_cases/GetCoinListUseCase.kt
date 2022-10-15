package com.example.criptoapp.domain.use_cases

import com.example.criptoapp.domain.repository.Repository

class GetCoinListUseCase(private val repository: Repository) {

    operator fun invoke() = repository.getCoinList()
}