package com.example.criptoapp.domain.reposytoryImpl

import com.example.criptoapp.data.entities.ResponseTop
import com.example.criptoapp.data.retrofit.CryptoApiFactory
import com.example.criptoapp.domain.repository.RetrofitRepository
import io.reactivex.Single

class RetrofitRepositoryImpl: RetrofitRepository {
    override fun getCoins(): Single<ResponseTop> {
        val apiFactory = CryptoApiFactory.getInstance()
        val apiService = apiFactory.getCryptoApiService()
        return apiService.getResponseTop()
    }
}