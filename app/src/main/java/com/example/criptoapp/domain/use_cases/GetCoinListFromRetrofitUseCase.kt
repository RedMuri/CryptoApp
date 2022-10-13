package com.example.criptoapp.domain.use_cases

import com.example.criptoapp.domain.repositories.RetrofitRepository

class GetCoinListFromRetrofitUseCase(private val repository: RetrofitRepository) {

    operator fun invoke() = repository.getCoinList()
}