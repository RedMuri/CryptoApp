package com.example.criptoapp.domain.repositories

import com.example.criptoapp.data.db_models.ResponseData
import com.example.criptoapp.data.db_models.ResponseTop
import retrofit2.Call

interface RetrofitRepository {

    suspend fun getCoinNameList(): Call<ResponseTop>
    suspend fun getCoinsInfoList(coinsName: String): Call<ResponseData>
}