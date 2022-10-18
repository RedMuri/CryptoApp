package com.example.criptoapp.di

import android.app.Application
import com.example.criptoapp.data.RepositoryImpl
import com.example.criptoapp.data.database.CryptoDao
import com.example.criptoapp.data.database.CryptoDatabase
import com.example.criptoapp.data.network.CryptoApiFactory
import com.example.criptoapp.data.network.CryptoApiService
import com.example.criptoapp.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository

    companion object{

        @ApplicationScope
        @Provides
        fun provideCryptoDao(application: Application): CryptoDao{
            return CryptoDatabase.getInstance(application).getCryptoDao()
        }

        @ApplicationScope
        @Provides
        fun provideApiService(): CryptoApiService{
            return CryptoApiFactory.cryptoApiService
        }
    }
}