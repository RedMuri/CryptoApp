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

class RetrofitRepositoryImpl: RetrofitRepository {

    private val mapper = CoinMapper()

    override suspend fun getCoinList(): List<CoinEntity> {
//        val responseTop = CryptoApiFactory.cryptoApiService.getResponseTop().enqueue(object : Callback<ResponseTop>{
//            override fun onResponse(call: Call<ResponseTop>, response: Response<ResponseTop>) {
//                Log.d("muri",response.toString())
//            }
//
//            override fun onFailure(call: Call<ResponseTop>, t: Throwable) {
//                Log.d("muri",t.toString())
//            }
//        })
//        val names = responseTop.data?.map { it.coinInfoName?.name }?.joinToString(",")
//        val responseData = CryptoApiFactory.cryptoApiService.getCoinsInfo(names!!)
//        val coins = getPriceListFromResponseData(responseData)
//        return mapper.mapDbModelListToEntity(coins)
        return listOf()
    }

    private fun getPriceListFromResponseData(responseData: ResponseData): List<CoinDbModel> {
        val result = ArrayList<CoinDbModel>()
        val jsonObject = responseData.raw ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val coinDbModel =
                    Gson().fromJson(currencyJson.getAsJsonObject(currencyKey), CoinDbModel::class.java)
                result.add(coinDbModel)
            }
        }
        return result
    }
}