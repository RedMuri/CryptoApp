package com.example.criptoapp.di

import android.app.Application
import com.example.criptoapp.data.RepositoryImpl
import com.example.criptoapp.data.database.CryptoDao
import com.example.criptoapp.data.database.CryptoDatabase
import com.example.criptoapp.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository

    companion object{

        @Provides

        fun provideCryptoDao(application: Application): CryptoDao{
            return CryptoDatabase.getInstance(application).getCryptoDao()
        }
    }
}