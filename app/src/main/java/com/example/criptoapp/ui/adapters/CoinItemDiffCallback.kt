package com.example.criptoapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.criptoapp.data.entities.Coin

class CoinItemDiffCallback : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin) =
        oldItem.firstName == newItem.firstName

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        val firstNames = oldItem.firstName == newItem.firstName
        val lastName = oldItem.lastName == newItem.lastName
        val min = oldItem.min == newItem.min
        val max = oldItem.max == newItem.max
        val update = oldItem.lastUpdate == newItem.lastUpdate
        val market = oldItem.lastMarket == newItem.lastMarket
        val price = oldItem.price == newItem.price
        val logo = oldItem.logoUrl == newItem.logoUrl
        return (firstNames && lastName && min && market && max && update && price && logo)
    }
}