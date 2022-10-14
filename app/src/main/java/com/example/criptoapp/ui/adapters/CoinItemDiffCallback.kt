package com.example.criptoapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.criptoapp.data.db_models.CoinDbModel

class CoinItemDiffCallback : DiffUtil.ItemCallback<CoinDbModel>() {
    override fun areItemsTheSame(oldItem: CoinDbModel, newItem: CoinDbModel) =
        oldItem.firstName == newItem.firstName

    override fun areContentsTheSame(oldItem: CoinDbModel, newItem: CoinDbModel): Boolean {
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