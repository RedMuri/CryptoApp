package com.example.criptoapp.data.mapper

import com.example.criptoapp.data.database.CoinDbModel
import com.example.criptoapp.data.network.model.CoinDto
import com.example.criptoapp.data.network.model.CoinJsonContainerDto
import com.example.criptoapp.data.network.model.CoinNameContainerListDto
import com.example.criptoapp.domain.entities.CoinEntity
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CoinMapper @Inject constructor(){

    fun mapDtoToDbModel(coinDto: CoinDto) = CoinDbModel(
        firstName = coinDto.firstName,
        lastName = coinDto.lastName,
        price = coinDto.price,
        lastUpdate = coinDto.lastUpdate,
        max = coinDto.max,
        min = coinDto.min,
        lastMarket = coinDto.lastMarket,
        logoUrl = BASE_IMAGE_URL + coinDto.logoUrl
    )

    fun mapDbModelToEntity(coinDbModel: CoinDbModel) = CoinEntity(
        firstName = coinDbModel.firstName,
        lastName = coinDbModel.lastName,
        price = coinDbModel.price,
        lastUpdate = convertMls(coinDbModel.lastUpdate),
        max = coinDbModel.max,
        min = coinDbModel.min,
        lastMarket = coinDbModel.lastMarket,
        logoUrl = coinDbModel.logoUrl
    )

    fun mapNameListToString(coinNameContainerListDto: CoinNameContainerListDto): String {
        return coinNameContainerListDto.nameContainerList?.map { it.nameContainer?.name }
            ?.joinToString(",") ?: ""
    }

    fun mapJsonContainerToListCoin(jsonContainer: CoinJsonContainerDto): List<CoinDto> {
        val result = mutableListOf<CoinDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val coinDto = Gson().fromJson(currencyJson.getAsJsonObject(currencyKey),
                    CoinDto::class.java)
                result.add(coinDto)
            }
        }
        return result
    }

    private fun convertMls(mls: Long?): String {
        if (mls == null) return ""
        val timestamp = Timestamp(mls*1000)
        val date = Date(timestamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object {

        private const val BASE_IMAGE_URL = "https://www.cryptocompare.com/"
    }
}