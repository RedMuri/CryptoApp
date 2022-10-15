package com.example.criptoapp.data.retrofit

import com.example.criptoapp.domain.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CryptoApiFactory {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Utils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val cryptoApiService: CryptoApiService = retrofit.create(CryptoApiService::class.java)
}