package com.example.criptoapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.criptoapp.R
import com.example.criptoapp.data.entities.Coin
import com.example.criptoapp.data.utils.Utils
import com.example.criptoapp.databinding.CoinItemBinding
import com.squareup.picasso.Picasso

class AdapterCoinList : ListAdapter<Coin,CoinViewHolder>(CoinItemDiffCallback()) {

    var onCoinClickListener: ((Coin) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = CoinItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = currentList[position]
        val binding = holder.binding
        Picasso.get().load(Utils.BASE_IMAGE_URL + coin.logoUrl).into(binding.imageViewCoinLogo)
        binding.textViewCoinName.text =
            String.format(binding.root.context.getString(R.string.coin_name_template),
                coin.firstName,
                coin.lastName)
        binding.textViewCoinPrice.text = coin.price.toString()
        binding.textViewLastUpdate.text =
            String.format(binding.root.context.getString(R.string.last_update_template),
                Utils.convertMls(coin.lastUpdate * 1000))
        binding.root.setOnClickListener {
            onCoinClickListener?.invoke(coin)
        }
    }
}