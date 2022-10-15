package com.example.criptoapp.data.network

import com.example.criptoapp.data.network.model.CoinJsonContainerDto
import com.example.criptoapp.data.network.model.CoinNameContainerListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApiService {
    @GET("top/totalvolfull")
    suspend fun getResponseTop(
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tsym: String = CURRENCY,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
    ): CoinNameContainerListDto

    @GET("pricemultifull?")
    suspend fun getCoinsInfo(
        @Query(QUERY_PARAM_FROM_SYMBOLS) fromSymbols: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) toSymbols: String = CURRENCY,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
    ): CoinJsonContainerDto

    companion object {
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_API_KEY = "api_key"

        private const val CURRENCY = "USD"
        private const val API_KEY = "0e5d65020b565c35d1c07ce1bff9ef3ebaf2d7cf5e3de951078fdc487c9f95bf"
    }

}