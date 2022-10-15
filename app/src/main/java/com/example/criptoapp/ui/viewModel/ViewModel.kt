package com.example.criptoapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.criptoapp.data.RepositoryImpl
import com.example.criptoapp.domain.entities.CoinEntity
import com.example.criptoapp.domain.use_cases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)

    private val getCoinListUseCase = GetCoinListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    private var _coinEntity = MutableLiveData<CoinEntity>()
    val coinEntity: LiveData<CoinEntity>
        get() = _coinEntity

    val coinListFromDb = getCoinListUseCase()


    init {
        loadDataUseCase.invoke()
    }

    fun getCoinInfo(coinName: String) {
        _coinEntity = getCoinInfoUseCase.invoke(coinName) as MutableLiveData<CoinEntity>
    }
}



