package com.example.criptoapp.data

import android.content.Context
import com.example.criptoapp.data.database.CryptoDatabase
import com.example.criptoapp.data.repository.DatabaseRepository

class DatabaseRepositoryImpl: DatabaseRepository {
    override fun getCryptoDao(database: CryptoDatabase) = database.getCryptoDao()
    override fun getDatabase(context: Context) = CryptoDatabase.getInstance(context)
}