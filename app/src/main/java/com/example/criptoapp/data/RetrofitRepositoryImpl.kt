package com.example.criptoapp.data

import android.util.Log
import com.example.criptoapp.data.db_models.CoinDbModel
import com.example.criptoapp.data.db_models.ResponseData
import com.example.criptoapp.data.db_models.ResponseTop
import com.example.criptoapp.data.retrofit.CryptoApiFactory
import com.example.criptoapp.domain.entities.CoinEntity
import com.example.criptoapp.domain.repositories.RetrofitRepository
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRepositoryImpl : RetrofitRepository {

    override suspend fun getCoinNameList(): Call<ResponseTop> {
        return CryptoApiFactory.cryptoApiService.getResponseTop()
    }

    override suspend fun getCoinsInfoList(coinsName: String): Call<ResponseData> {
        return CryptoApiFactory.cryptoApiService.getCoinsInfo(coinsName)
    }
}