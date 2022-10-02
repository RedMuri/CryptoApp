package com.example.criptoapp.domain.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.criptoapp.data.entities.Coin
import com.example.criptoapp.domain.reposytoryImpl.DatabaseRepositoryImpl
import com.example.criptoapp.domain.reposytoryImpl.RetrofitRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val retrofitRepository = RetrofitRepositoryImpl()
    private val databaseRepository = DatabaseRepositoryImpl()
    private val compositeDisposable = CompositeDisposable()
    private val database = databaseRepository.getDatabase(application.applicationContext)
    private val coins = database.getCryptoDao().getAllCoins()

    init {
        loadData()
    }

    private fun loadData() {
        compositeDisposable.add(retrofitRepository.getCoins()
            .map { res -> res.listOfCoins.map { it.raw.coin } }
            .subscribeOn(Schedulers.io())
            .delaySubscription(10,TimeUnit.SECONDS)
            .repeat()
            .retry()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                insertCoin(it)
            }, {
                Log.i("muri", it.message.toString())
            }))
    }

    fun getCoinByLogoUrl(logoUrl: String): LiveData<List<Coin>> =
        databaseRepository.getCryptoDao(database).getCoinByLogoUrl(logoUrl)


    fun deleteAllCoins(coins: List<Coin>) {
        compositeDisposable.add(
            databaseRepository.getCryptoDao(database).deleteAllCoins().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    insertCoin(coins)
                }, {
                    it.printStackTrace()
                }))
    }

    private fun insertCoin(coins: List<Coin>) {
        compositeDisposable.add(databaseRepository.getCryptoDao(database).insertCoins(coins)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("medina", "inserted: $coins")
            }, {
                it.printStackTrace()
            }))
    }

    fun getAllCoins() = this.coins
}