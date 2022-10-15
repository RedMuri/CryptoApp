package com.example.criptoapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.criptoapp.data.database.CryptoDatabase
import com.example.criptoapp.data.mapper.CoinMapper
import com.example.criptoapp.data.network.CryptoApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context,workerParameters) {

    private val cryptoDao = CryptoDatabase.getInstance(context).getCryptoDao()
    private val apiService = CryptoApiFactory.cryptoApiService

    private val mapper = CoinMapper()

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val coinNames = apiService.getResponseTop()
                val names = mapper.mapNameListToString(coinNames)
                val coins = apiService.getCoinsInfo(names)
                val coinsDto = mapper.mapJsonContainerToListCoin(coins)
                val coinsDb = coinsDto.map { mapper.mapDtoToDbModel(it) }
                cryptoDao.insertCoins(coinsDb)
            } catch (_: Exception) {
            }
            delay(10000)
        }
    }

    companion object{

        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}