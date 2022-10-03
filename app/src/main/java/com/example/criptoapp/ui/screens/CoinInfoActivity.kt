package com.example.criptoapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.criptoapp.R
import com.example.criptoapp.data.utils.Utils
import com.example.criptoapp.domain.viewModel.ViewModel
import com.squareup.picasso.Picasso

class CoinInfoActivity : AppCompatActivity() {
    companion object{
        private const val FROM_SYMBOL_EXTRA = "fsym"
        fun newIntent(context: Context, fromSymbol: String): Intent{
            val intent = Intent(context,CoinInfoActivity::class.java)
            intent.putExtra(FROM_SYMBOL_EXTRA,fromSymbol)
            return intent
        }
    }
    private lateinit var imageView: ImageView
    private lateinit var name: TextView
    private lateinit var price: TextView
    private lateinit var max: TextView
    private lateinit var min: TextView
    private lateinit var lastUpdate: TextView
    private lateinit var lastMarket: TextView
    private lateinit var viewModel: ViewModel
    private lateinit var firstName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_info)
        if (!intent.hasExtra(FROM_SYMBOL_EXTRA)){
            finish()
            return
        }
        imageView = findViewById(R.id.imageView)
        name = findViewById(R.id.coinInfoNameValue)
        price = findViewById(R.id.coinInfoPriceValue)
        max = findViewById(R.id.coinInfoMaxValue)
        min = findViewById(R.id.coinInfoMinValue)
        lastUpdate = findViewById(R.id.coinInfoLastUpdateValue)
        lastMarket = findViewById(R.id.coinInfoLastMarketValue)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        firstName = intent.getStringExtra(FROM_SYMBOL_EXTRA).toString()

    }

    override fun onResume() {
        if (firstName!="null") {
            viewModel.getCoinByFirstName(firstName).observe(this){ listOfCoins ->
                val coin = listOfCoins[0]
                Picasso.get().load(Utils.BASE_IMAGE_URL + coin.logoUrl).into(imageView)
                name.text = String.format(getString(R.string.coin_name_template),coin.firstName,coin.lastName)
                price.text = coin.price.toString()
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