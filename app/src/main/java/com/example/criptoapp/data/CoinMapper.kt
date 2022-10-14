package com.example.criptoapp.data

import com.example.criptoapp.data.db_models.CoinDbModel
import com.example.criptoapp.domain.entities.CoinEntity

class CoinMapper {

    fun mapEntityToDbModel(coinEntity: CoinEntity) = CoinDbModel(
        firstName = coinEntity.firstName,
        lastName = coinEntity.lastName,
        price = coinEntity.price,
        lastUpdate = coinEntity.lastUpdate,
        max = coinEntity.max,
        min = coinEntity.min,
        lastMarket = coinEntity.lastMarket,
        logoUrl = coinEntity.logoUrl
    )

    fun mapDbModelToEntity(coinDbModel: CoinDbModel) = CoinEntity(
        firstName = coinDbModel.firstName,
        lastName = coinDbModel.lastName,
        price = coinDbModel.price,
        lastUpdate = coinDbModel.lastUpdate,
        max = coinDbModel.max,
        min = coinDbModel.min,
        lastMarket = coinDbModel.lastMarket,
        logoUrl = coinDbModel.logoUrl
    )

    fun mapDbModelListToEntity(coinsFromDb: List<CoinDbModel>): List<CoinEntity> {
        return coinsFromDb.map { mapDbModelToEntity(it) }
    }

    fun mapEntityListToDbModel(coinsEntity: List<CoinEntity>): List<CoinDbModel> {
        return coinsEntity.map { mapEntityToDbModel(it) }
    }
}