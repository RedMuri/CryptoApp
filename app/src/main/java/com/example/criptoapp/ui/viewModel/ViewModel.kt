package com.example.criptoapp.ui.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.criptoapp.data.db_models.CoinDbModel
import com.example.criptoapp.data.db_models.ResponseData
import com.example.criptoapp.data.DatabaseRepositoryImpl
import com.example.criptoapp.data.RetrofitRepositoryImpl
import com.example.criptoapp.domain.entities.CoinEntity
import com.example.criptoapp.domain.use_cases.GetCoinListFromDbUseCase
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val retrofitRepository = RetrofitRepositoryImpl()
    private val databaseRepository = DatabaseRepositoryImpl(application)

    private val getCoinListDbUseCase = GetCoinListFromDbUseCase(databaseRepository)

    private var _coinDbModelByName = MutableLiveData<CoinEntity>()
    val coinDbModelByName: LiveData<CoinEntity>
        get() = _coinDbModelByName

    init {
        loadData()
    }

    private fun loadData() {

    }

    fun getCoinByFirstName(firstName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _coinDbModelByName.postValue(
                databaseRepository.getCoinInfoByName(firstName))
        }
    }

    private fun insertCoin(coinDbModels: List<CoinDbModel>) {

    }

    fun getAllCoins() = this.coins
}


