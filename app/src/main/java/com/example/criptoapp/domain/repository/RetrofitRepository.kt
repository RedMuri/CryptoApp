package com.example.criptoapp.domain.repository

import com.example.criptoapp.data.entities.ResponseData
import com.example.criptoapp.data.entities.ResponseTop
import io.reactivex.Single

interface RetrofitRepository {
    fun getResponseTop(): Single<ResponseTop>
    fun getCoinsInfo(fromSymbols: String): Single<ResponseData>
}