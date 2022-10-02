package com.example.criptoapp.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.criptoapp.R
import com.example.criptoapp.data.utils.Utils
import com.example.criptoapp.domain.viewModel.ViewModel
import com.squareup.picasso.Picasso
import kotlin.math.log

class CoinInfoActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var name: TextView
    private lateinit var max: TextView
    private lateinit var min: TextView
    private lateinit var lastUpdate: TextView
    private lateinit var lastMarket: TextView
    private lateinit var viewModel: ViewModel
    private lateinit var logoUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_info)
        imageView = findViewById(R.id.imageView)
        name = findViewById(R.id.coinInfoNameValue)
        max = findViewById(R.id.coinInfoMaxValue)
        min = findViewById(R.id.coinInfoMinValue)
        lastUpdate = findViewById(R.id.coinInfoLastUpdateValue)
        lastMarket = findViewById(R.id.coinInfoLastMarketValue)
        viewModel = ViewModel(application)
        logoUrl = intent.getStringExtra("logoUrl").toString()

    }

    override fun onResume() {
        if (logoUrl!="null") {
            viewModel.getCoinByLogoUrl(logoUrl).observe(this){listOfCoins ->
                val coin = listOfCoins[0]
                Picasso.get().load(Utils.BASE_IMAGE_URL + coin.logoUrl).into(imageView)
                name.text = coin.firstName + "/" + coin.lastName
                max.text = coin.max.toString()
                min.text = coin.min.toString()
                lastUpdate.text = Utils.convertMls(coin.lastUpdate*1000)
                lastMarket.text = coin.lastMarket
            }
        } else
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        super.onResume()
    }
}