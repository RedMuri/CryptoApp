package com.example.criptoapp.data.retrofit

import com.example.criptoapp.data.utils.Utils
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoApiFactory private constructor(
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(Utils.BASE_URL)
        .build()
) {
    companion object{
        private var cryptoApiFactory: CryptoApiFactory? = null

        fun getInstance(): CryptoApiFactory{
            if (cryptoApiFactory==null)
                cryptoApiFactory = CryptoApiFactory()
            return cryptoApiFactory as CryptoApiFactory
        }
    }
    fun getCryptoApiService(): CryptoApiService = retrofit.create(CryptoApiService::class.java)
}