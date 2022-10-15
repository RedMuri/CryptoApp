package com.example.criptoapp.domain.use_cases

import com.example.criptoapp.domain.repositories.RetrofitRepository

class GetCoinNameListFromRetrofitUseCase(private val repository: RetrofitRepository) {

    suspend operator fun invoke() = repository.getCoinNameList()
}
