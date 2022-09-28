package com.example.criptoapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.criptoapp.R
import com.example.criptoapp.data.entities.Coin
import com.squareup.picasso.Picasso

class AdapterCoinList: Adapter<AdapterCoinList.CoinViewHolder>() {
    private var coins = arrayListOf<Coin>()
    inner class CoinViewHolder(itemView: View) : ViewHolder(itemView){
        val logo: ImageView = itemView.findViewById(R.id.imageViewCoinLogo)
        val name: TextView = itemView.findViewById(R.id.textViewCoinName)
        val price: TextView = itemView.findViewById(R.id.textViewCoinPrice)
        val lastUpdate: TextView = itemView.findViewById(R.id.textViewLastUpdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coin_item,parent,false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coins[position]
        Picasso.get().load("coin.logoUrl").into(holder.logo)
        holder.name.text = coin.firstName + "/" + coin.lastName
        holder.price.text = coin.price.toString()
        holder.lastUpdate.text = coin.lastUpdate.toString()
    }

    override fun getItemCount() = coins.size
}