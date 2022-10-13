package com.example.criptoapp.ui.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.criptoapp.data.entities.Coin
import com.example.criptoapp.data.entities.ResponseData
import com.example.criptoapp.data.DatabaseRepositoryImpl
import com.example.criptoapp.data.RetrofitRepositoryImpl
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val retrofitRepository = RetrofitRepositoryImpl()
    private val databaseRepository = DatabaseRepositoryImpl()
    private val compositeDisposable = CompositeDisposable()
    private val database = databaseRepository.getDatabase(application.applicationContext)
    private val coins = database.getCryptoDao().getAllCoins()

    private var _coinByName = MutableLiveData<Coin>()
    val coinByName: LiveData<Coin>
        get() = _coinByName

    init {
        loadData()
    }

    private fun loadData() {
        compositeDisposable.add(retrofitRepository.getResponseTop()
            .map { res ->
                Log.i("muri", "map: $res")
                res.data?.map { it.coinInfoName?.name }?.joinToString(",")
            }
            .flatMap { retrofitRepository.getCoinsInfo(it) }
            .map { getPriceListFromResponseData(it) }
            .subscribeOn(Schedulers.io())
            .delaySubscription(3, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                insertCoin(it)
            }, {
                Log.i("muri", it.message.toString())
            }))
    }

    private fun getPriceListFromResponseData(responseData: ResponseData): List<Coin> {
        val result = ArrayList<Coin>()
        val jsonObject = responseData.raw ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val coin =
                    Gson().fromJson(currencyJson.getAsJsonObject(currencyKey), Coin::class.java)
                result.add(coin)
            }
        }
        return result
    }


    fun getCoinByFirstName(firstName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _coinByName.postValue(
                databaseRepository.getCryptoDao(database).getCoinByFirstName(firstName))
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
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


