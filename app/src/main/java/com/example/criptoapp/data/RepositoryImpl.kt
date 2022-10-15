package com.example.criptoapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.criptoapp.data.database.CryptoDatabase
import com.example.criptoapp.data.mapper.CoinMapper
import com.example.criptoapp.data.workers.RefreshDataWorker
import com.example.criptoapp.domain.entities.CoinEntity
import com.example.criptoapp.domain.repository.Repository

class RepositoryImpl(private val application: Application) : Repository {

    private val cryptoDao = CryptoDatabase.getInstance(application).getCryptoDao()

    private val mapper = CoinMapper()

    override fun getCoinList(): LiveData<List<CoinEntity>> {
        val coinsFromDb = cryptoDao.getAllCoins()
        val coinsEntity = Transformations.map(coinsFromDb) {
            it.map { mapper.mapDbModelToEntity(it) }
        }
        return coinsEntity
    }

    override fun getCoinInfo(coinName: String): LiveData<CoinEntity> {
        return Transformations.map(cryptoDao.getCoinByFirstName(coinName)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}