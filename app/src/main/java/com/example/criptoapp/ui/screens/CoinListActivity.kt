package com.example.criptoapp.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.criptoapp.R
import com.example.criptoapp.data.retrofit.CryptoApiFactory
import com.example.criptoapp.data.utils.Utils
import com.example.criptoapp.domain.viewModel.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CoinListActivity : AppCompatActivity() {
    private lateinit var recyclerViewCoinList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_list)
        recyclerViewCoinList = findViewById(R.id.recyclerViewCoinList)
        val viewModel = ViewModel(application)
        val coins = viewModel.getAllCoins().observe(this) { coins ->
            Log.i("muri",coins.toString())
        }
        viewModel.loadData()
    }
}