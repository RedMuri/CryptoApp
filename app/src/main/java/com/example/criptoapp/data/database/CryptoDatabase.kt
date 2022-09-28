package com.example.criptoapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.criptoapp.data.entities.Coin

@Database(entities = [Coin::class], version = 1)
abstract class CryptoDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "coins.db"
        private var database: CryptoDatabase? = null
        private var LOCK = Any()

        fun getInstance(context: Context): CryptoDatabase {
            synchronized(LOCK) {
                if (database == null)
                    database =
                        Room.databaseBuilder(context, CryptoDatabase::class.java, DB_NAME).build()
                return database as CryptoDatabase
            }
        }
    }
    abstract fun getCryptoDao(): CryptoDao
}