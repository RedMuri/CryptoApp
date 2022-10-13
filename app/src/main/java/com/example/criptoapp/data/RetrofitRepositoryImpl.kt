package com.example.criptoapp.data

import com.example.criptoapp.data.entities.ResponseData
import com.example.criptoapp.data.entities.ResponseTop
import com.example.criptoapp.data.retrofit.CryptoApiFactory
import com.example.criptoapp.data.repository.RetrofitRepository
import io.reactivex.Single

class RetrofitRepositoryImpl: RetrofitRepository {
    override fun getResponseTop(): Single<ResponseTop> {
        val apiFactory = CryptoApiFactory
        val apiService = apiFactory.cryptoApiService
        return apiService.getResponseTop()
    }
    override fun getCoinsInfo(fromSymbols: String): Single<ResponseData> {
        val apiFactory = CryptoApiFactory
        val apiService = apiFactory.cryptoApiService
        return apiService.getCoinsInfo(fromSymbols = fromSymbols)
    }
}