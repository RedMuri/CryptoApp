package com.example.criptoapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.criptoapp.data.db_models.CoinDbModel
import com.example.criptoapp.domain.entities.CoinEntity

class CoinItemDiffCallback : DiffUtil.ItemCallback<CoinEntity>() {

    override fun areItemsTheSame(oldItem: CoinEntity, newItem: CoinEntity): Boolean =
        oldItem.firstName == newItem.firstName

    override fun areContentsTheSame(oldItem: CoinEntity, newItem: CoinEntity): Boolean =
        oldItem==newItem
}