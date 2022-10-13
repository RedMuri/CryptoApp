package com.example.criptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.criptoapp.data.entities.Coin
import io.reactivex.Completable

@Dao
interface CryptoDao {
    @Query("SELECT * FROM coins order by lastUpdate desc")
    fun getAllCoins(): LiveData<List<Coin>>
    @Query("SELECT * FROM coins WHERE firstName == :firstName limit 1")
    fun getCoinByFirstName(firstName: String): Coin
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoins(coins: List<Coin>): Completable
    @Delete
    fun deleteCoin(coin: Coin)
    @Query("DELETE FROM coins")
    fun deleteAllCoins(): Completable
}