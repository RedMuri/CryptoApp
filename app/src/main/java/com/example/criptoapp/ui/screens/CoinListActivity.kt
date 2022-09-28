package com.example.criptoapp.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.criptoapp.R
import com.example.criptoapp.data.retrofit.CryptoApiFactory
import com.example.criptoapp.data.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CoinListActivity : AppCompatActivity() {
    private lateinit var recyclerViewCoinList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_list)
        recyclerViewCoinList = findViewById(R.id.recyclerViewCoinList)
//        val apiFactory = CryptoApiFactory.getInstance()
//        val apiService = apiFactory?.getCryptoApiService()
//        apiService?.let {
//            it.getResponseTop("totalvolfull","USD",10,Utils.API_KEY)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    Log.i("main", it.toString())
//                }, {
//                    Log.i("main", it.toString())
//                })
//        }
    }
}