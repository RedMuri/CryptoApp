package com.example.criptoapp.data.retrofit

import com.example.criptoapp.data.utils.Utils
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CryptoApiFactory {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(Utils.BASE_URL)
        .build()
    val cryptoApiService: CryptoApiService = retrofit.create(CryptoApiService::class.java)
}