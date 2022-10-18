package com.example.criptoapp.domain.use_cases

import com.example.criptoapp.domain.repository.Repository
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(coinName: String) = repository.getCoinInfo(coinName)
}
