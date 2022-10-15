package com.example.criptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.criptoapp.data.db_models.CoinDbModel
import io.reactivex.Completable

@Dao
interface CryptoDao {
    @Query("SELECT * FROM coins order by lastUpdate desc")
    fun getAllCoins(): LiveData<List<CoinDbModel>>
    @Query("SELECT * FROM coins WHERE firstName == :firstName limit 1")
    suspend fun getCoinByFirstName(firstName: String): CoinDbModel
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(coinDbModels: List<CoinDbModel>)
    @Delete
    suspend fun deleteCoin(coinDbModel: CoinDbModel)
    @Query("DELETE FROM coins")
    suspend fun deleteAllCoins()
}