package com.example.criptoapp.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.LayoutDirection
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.criptoapp.R
import com.example.criptoapp.data.retrofit.CryptoApiFactory
import com.example.criptoapp.data.utils.Utils
import com.example.criptoapp.domain.viewModel.ViewModel
import com.example.criptoapp.ui.adapters.AdapterCoinList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.intellij.lang.annotations.Language
import java.text.SimpleDateFormat
import java.util.*

class CoinListActivity : AppCompatActivity() {
    private lateinit var recyclerViewCoinList: RecyclerView
    private lateinit var adapterCoinList: AdapterCoinList
    private lateinit var viewModel: ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_list)
        recyclerViewCoinList = findViewById(R.id.recyclerViewCoinList)
        adapterCoinList = AdapterCoinList()
        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        recyclerViewCoinList.adapter = adapterCoinList
        recyclerViewCoinList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        attachListener(adapterCoinList)
    }


    private fun attachListener(adapterCoinList: AdapterCoinList) {
        adapterCoinList.setOnCoinClickListener(object : AdapterCoinList.OnCoinClickListener {
            override fun onCoinClick(position: Int) {
                val intent = Intent(this@CoinListActivity, CoinInfoActivity::class.java)
                intent.putExtra("logoUrl", adapterCoinList.getCoins()[position].logoUrl)
                startActivity(intent)
            }
        })
    }

    override fun onResume() {
        viewModel.getAllCoins().observe(this) { coinsFromDB ->
            Log.i("muri", coinsFromDB.toString())
            adapterCoinList.setCoins(coinsFromDB)
        }
        super.onResume()
    }
}