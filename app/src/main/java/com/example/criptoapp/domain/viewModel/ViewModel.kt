package com.example.criptoapp.domain.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.criptoapp.data.entities.Coin
import com.example.criptoapp.domain.reposytoryImpl.DatabaseRepositoryImpl
import com.example.criptoapp.domain.reposytoryImpl.RetrofitRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val retrofitRepository = RetrofitRepositoryImpl()
    private val databaseRepository = DatabaseRepositoryImpl()
    private val compositeDisposable = CompositeDisposable()
    private val database = databaseRepository.getDatabase(application.applicationContext)
    private val coins = database.getCryptoDao().getAllCoins()

    fun loadData() {
        compositeDisposable.add(retrofitRepository.getCoins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ responseTop ->
                insertCoin(responseTop.listOfCoins.map { it.raw.coin })
            }, {
                it.printStackTrace()
            }))
    }

    fun deleteAllCoins() {
        compositeDisposable.add(
            databaseRepository.getCryptoDao(database).deleteAllCoins().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i("muri", "viewModel: DeletedAll")
                }, {
                    it.printStackTrace()
                }))
    }

    private fun insertCoin(coins: List<Coin>) {
        compositeDisposable.add(databaseRepository.getCryptoDao(database).insertCoins(coins)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("muri", "viewModel: Inserted")
            }, {
                it.printStackTrace()
            }))
    }

    fun getAllCoins() = this.coins
}