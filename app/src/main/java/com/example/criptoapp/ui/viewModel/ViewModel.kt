package com.example.criptoapp.ui.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.criptoapp.data.CoinMapper
import com.example.criptoapp.data.DatabaseRepositoryImpl
import com.example.criptoapp.data.RetrofitRepositoryImpl
import com.example.criptoapp.data.db_models.CoinDbModel
import com.example.criptoapp.data.db_models.ResponseData
import com.example.criptoapp.data.db_models.ResponseTop
import com.example.criptoapp.domain.entities.CoinEntity
import com.example.criptoapp.domain.use_cases.*
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val mapper = CoinMapper()

    private val retrofitRepository = RetrofitRepositoryImpl()
    private val databaseRepository = DatabaseRepositoryImpl(application)

    private val getCoinListDbUseCase = GetCoinListFromDbUseCase(databaseRepository)
    private val getCoinNameListFromRetrofitUseCase =
        GetCoinNameListFromRetrofitUseCase(retrofitRepository)
    private val getCoinsInfoListFromRetrofitUseCase =
        GetCoinsInfoListFromRetrofitUseCase(retrofitRepository)
    private val insertCoinsInDbUseCase = InsertCoinsInDbUseCase(databaseRepository)
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
            getCoinNameListFromRetrofitUseCase.invoke().enqueue(object : Callback<ResponseTop> {
                override fun onResponse(
                    call: Call<ResponseTop>,
                    response: Response<ResponseTop>,
                ) {
                    val names =
                        response.body()?.data?.map { it.coinInfoName?.name }?.joinToString(",")
                    names?.let { getCoinsInfo(it) }
                }

                override fun onFailure(call: Call<ResponseTop>, t: Throwable) {
                    Log.e("muri", "loadData: $t")
                }
            })
        }
    }

    private fun getCoinsInfo(coinsName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getCoinsInfoListFromRetrofitUseCase.invoke(coinsName).enqueue(object : Callback<ResponseData> {
                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>,
                ) {
                    response.body()?.let {
                        val coins = getPriceListFromResponseData(it)
                        Log.d("muri", coins.toString())
                        insertCoinsInDb(mapper.mapDbModelListToEntity(coins))
                    }
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    Log.e("muri", t.toString())
                }
            })
        }
    }

    private fun insertCoinsInDb(coins: List<CoinEntity>){
        viewModelScope.launch(Dispatchers.IO){
            insertCoinsInDbUseCase.invoke(coins)
        }
    }

    fun getCoinByFirstName(firstName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val coinEntity = getCoinInfoFromDbUseCase.invoke(firstName)
            _coinDbModelByName.postValue(coinEntity)
        }
    }

    private fun getPriceListFromResponseData(responseData: ResponseData): List<CoinDbModel> {
        val result = ArrayList<CoinDbModel>()
        val jsonObject = responseData.raw ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val coinDbModel = Gson().fromJson(currencyJson.getAsJsonObject(currencyKey),
                    CoinDbModel::class.java)
                result.add(coinDbModel)
            }
        }
        return result
    }
}



