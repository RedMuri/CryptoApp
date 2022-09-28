package com.example.criptoapp.data.retrofit

import com.example.criptoapp.data.entities.ResponseTop
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApiService {
    @GET("top/{totalvolfull}")
    fun getResponseTop(
        @Path("totalvolfull") totalvolfull: String,
        @Query("tsym") tsym: String,
        @Query("limit") limit: Int,
        @Query("api_key") apiKey: String
    ): Single<ResponseTop>
    //https://min-api.cryptocompare.com/data/top/totalvolfull?limit=10&tsym=USD
}