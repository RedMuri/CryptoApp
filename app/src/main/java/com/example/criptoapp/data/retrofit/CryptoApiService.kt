package com.example.criptoapp.data.retrofit

import com.example.criptoapp.data.db_models.ResponseData
import com.example.criptoapp.data.db_models.ResponseTop
import com.example.criptoapp.domain.Utils
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApiService {
    @GET("top/totalvolfull")
    fun getResponseTop(
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tsym: String = CURRENCY,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = Utils.API_KEY,
    ): Call<ResponseTop>

    @GET("pricemultifull?")
    fun getCoinsInfo(
        @Query(QUERY_PARAM_FROM_SYMBOLS) fromSymbols: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) toSymbols: String = CURRENCY,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = Utils.API_KEY,
    ): Call<ResponseData>

    companion object {
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_API_KEY = "api_key"

        private const val CURRENCY = "USD"
    }

}