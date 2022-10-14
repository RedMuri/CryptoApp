package com.example.criptoapp.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.criptoapp.data.database.CryptoDatabase
import com.example.criptoapp.domain.entities.CoinEntity
import com.example.criptoapp.domain.repositories.DatabaseRepository

class DatabaseRepositoryImpl(context: Context) : DatabaseRepository {

    private val database = CryptoDatabase.getInstance(context)
    private val dao = database.getCryptoDao()
    private val mapper = CoinMapper()

    override fun getCoinList(): LiveData<List<CoinEntity>> {
        val coinsFromDb = dao.getAllCoins()
        val coinsEntity = Transformations.map(coinsFromDb){
            mapper.mapDbModelListToEntity(it)
        }
        return coinsEntity
    }

    override suspend fun getCoinInfoByName(coinName: String): CoinEntity {
        return mapper.mapDbModelToEntity(dao.getCoinByFirstName(coinName))
    }

    override suspend fun insertCoins(coins: List<CoinEntity>) {
        val coinsEntity = mapper.mapEntityListToDbModel(coins)
        dao.insertCoins(coinsEntity)
    }
}