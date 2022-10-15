package com.example.criptoapp.domain.use_cases

import com.example.criptoapp.domain.repositories.RetrofitRepository

class GetCoinsInfoListFromRetrofitUseCase(private val repository: RetrofitRepository) {

    suspend operator fun invoke(coinsName: String) = repository.getCoinsInfoList(coinsName)
}
