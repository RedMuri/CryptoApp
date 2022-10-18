package com.example.criptoapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.criptoapp.domain.entities.CoinEntity
import com.example.criptoapp.domain.use_cases.GetCoinInfoUseCase
import com.example.criptoapp.domain.use_cases.GetCoinListUseCase
import com.example.criptoapp.domain.use_cases.LoadDataUseCase
import javax.inject.Inject


class CoinViewModel @Inject constructor(
    private val getCoinListUseCase: GetCoinListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase,
) : ViewModel() {

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



