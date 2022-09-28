package com.example.criptoapp.domain.repository

import android.content.Context
import com.example.criptoapp.data.database.CryptoDao
import com.example.criptoapp.data.database.CryptoDatabase

interface DatabaseRepository {
    fun getCryptoDao(database: CryptoDatabase): CryptoDao
    fun getDatabase(context: Context): CryptoDatabase
}