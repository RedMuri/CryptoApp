package com.example.criptoapp.data.retrofit

import com.example.criptoapp.domain.Utils
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CryptoApiFactory {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Utils.BASE_URL)
        .build()
    val cryptoApiService: CryptoApiService = retrofit.create(CryptoApiService::class.java)
}