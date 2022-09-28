package com.example.criptoapp.data.retrofit

import com.example.criptoapp.data.entities.ResponseTop
import com.example.criptoapp.data.utils.Utils
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApiService {
    @GET("top/{totalvolfull}")
    fun getResponseTop(
        @Path("totalvolfull") totalvolfull: String = "totalvolfull",
        @Query("tsym") tsym: String = "USD",
        @Query("limit") limit: Int = 10,
        @Query("api_key") apiKey: String = Utils.API_KEY
    ): Single<ResponseTop>
}