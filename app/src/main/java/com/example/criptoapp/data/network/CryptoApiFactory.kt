package com.example.criptoapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CryptoApiFactory {
    private const val BASE_URL = "https://min-api.cryptocompare.com/data/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val cryptoApiService: CryptoApiService = retrofit.create(CryptoApiService::class.java)
}