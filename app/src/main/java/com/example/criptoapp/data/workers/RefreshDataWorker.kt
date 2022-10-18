package com.example.criptoapp.data.workers

import android.content.Context
import androidx.work.*
import com.example.criptoapp.data.database.CryptoDao
import com.example.criptoapp.data.mapper.CoinMapper
import com.example.criptoapp.data.network.CryptoApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val cryptoDao: CryptoDao,
    private val apiService: CryptoApiService,
    private val mapper: CoinMapper,
) : CoroutineWorker(context, workerParameters) {

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

    companion object {

        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }

    class Factory @Inject constructor(
        private val cryptoDao: CryptoDao,
        private val apiService: CryptoApiService,
        private val mapper: CoinMapper,
    ) : ChildWorkerFactory {

        override fun create(
            context: Context,
            workerParameters: WorkerParameters,
        ): ListenableWorker {
            return RefreshDataWorker(
                context,
                workerParameters,
                cryptoDao,
                apiService,
                mapper
            )
        }
    }
}