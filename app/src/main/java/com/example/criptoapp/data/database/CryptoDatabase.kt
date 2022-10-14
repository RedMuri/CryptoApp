package com.example.criptoapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.criptoapp.data.db_models.CoinDbModel

@Database(entities = [CoinDbModel::class], version = 8)
abstract class CryptoDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "coins.db"
        private var database: CryptoDatabase? = null
        private var LOCK = Any()

        fun getInstance(context: Context): CryptoDatabase {
            synchronized(LOCK) {
                database?.let { return it }
                val instance = Room.databaseBuilder(context, CryptoDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration().build()
                database = instance
                return instance
            }
        }
    }

    abstract fun getCryptoDao(): CryptoDao
}