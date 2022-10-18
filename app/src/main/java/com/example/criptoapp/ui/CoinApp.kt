package com.example.criptoapp.ui

import android.app.Application
import androidx.work.Configuration
import com.example.criptoapp.data.workers.CoinWorkerFactory
import com.example.criptoapp.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp: Application(), Configuration.Provider {

    @Inject
    lateinit var coinWorkerFactory: CoinWorkerFactory

    val component by lazy {
        DaggerApplicationComponent
            .factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(coinWorkerFactory)
            .build()
    }
}