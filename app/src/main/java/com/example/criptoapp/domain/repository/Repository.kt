package com.example.criptoapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.criptoapp.domain.entities.CoinEntity

interface Repository {

    fun getCoinList(): LiveData<List<CoinEntity>>
    fun getCoinInfo(coinName: String): LiveData<CoinEntity>
    fun loadData()
}