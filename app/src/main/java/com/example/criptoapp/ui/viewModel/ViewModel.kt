package com.example.criptoapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.criptoapp.data.DatabaseRepositoryImpl
import com.example.criptoapp.data.RetrofitRepositoryImpl
import com.example.criptoapp.data.retrofit.CryptoApiFactory
import com.example.criptoapp.domain.entities.CoinEntity
import com.example.criptoapp.domain.use_cases.GetCoinInfoFromDbUseCase
import com.example.criptoapp.domain.use_cases.GetCoinListFromDbUseCase
import com.example.criptoapp.domain.use_cases.GetCoinListFromRetrofitUseCase
import com.example.criptoapp.domain.use_cases.InsertCoinsFromDbUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val retrofitRepository = RetrofitRepositoryImpl()
    private val databaseRepository = DatabaseRepositoryImpl(application)

    private val getCoinListDbUseCase = GetCoinListFromDbUseCase(databaseRepository)
    private val getCoinListFromRetrofitUseCase = GetCoinListFromRetrofitUseCase(retrofitRepository)
    private val insertCoinsFromDbUseCase = InsertCoinsFromDbUseCase(databaseRepository)
    private val getCoinInfoFromDbUseCase = GetCoinInfoFromDbUseCase(databaseRepository)

    private var _coinDbModelByName = MutableLiveData<CoinEntity>()
    val coinDbModelByName: LiveData<CoinEntity>
        get() = _coinDbModelByName

    val coinListFromDb = getCoinListDbUseCase()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            //val coinsFromRetrofit = getCoinListFromRetrofitUseCase.invoke()
            //insertCoinsFromDbUseCase.invoke(coinsFromRetrofit)
            val res = CryptoApiFactory.cryptoApiService.getResponseTop()
        }
    }

    fun getCoinByFirstName(firstName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val coinEntity = getCoinInfoFromDbUseCase.invoke(firstName)
            _coinDbModelByName.postValue(coinEntity)
        }
    }
}


