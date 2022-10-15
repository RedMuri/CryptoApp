package com.example.criptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CryptoDao {
    @Query("SELECT * FROM coins order by lastUpdate desc")
    fun getAllCoins(): LiveData<List<CoinDbModel>>
    @Query("SELECT * FROM coins WHERE firstName == :firstName limit 1")
    fun getCoinByFirstName(firstName: String): LiveData<CoinDbModel>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(coinDbModels: List<CoinDbModel>)

}