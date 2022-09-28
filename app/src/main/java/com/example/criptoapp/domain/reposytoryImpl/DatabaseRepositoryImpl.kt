package com.example.criptoapp.domain.reposytoryImpl

import android.content.Context
import com.example.criptoapp.data.database.CryptoDatabase
import com.example.criptoapp.domain.repository.DatabaseRepository

class DatabaseRepositoryImpl: DatabaseRepository {
    override fun getCryptoDao(database: CryptoDatabase) = database.getCryptoDao()
    override fun getDatabase(context: Context) = CryptoDatabase.getInstance(context)
}