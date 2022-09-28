package com.example.criptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.criptoapp.data.entities.Coin

@Dao
interface CryptoDao {
    @Query("SELECT * FROM coins")
    fun getAllCoins(): LiveData<List<Coin>>
    @Query("SELECT * FROM coins where id == :coinId")
    fun getCoinById(coinId: Int)
    @Insert(entity = Coin::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertCoins(coins: List<Coin>)
    @Delete(entity = Coin::class)
    fun deleteCoins(coinId: Int)
    @Query("DELETE FROM coins")
    fun deleteAllCoins()
}