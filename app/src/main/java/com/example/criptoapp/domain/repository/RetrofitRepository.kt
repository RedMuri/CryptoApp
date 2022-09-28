package com.example.criptoapp.domain.repository

import com.example.criptoapp.data.entities.ResponseTop
import io.reactivex.Single

interface RetrofitRepository {
    fun getCoins(): Single<ResponseTop>
}