package com.example.criptoapp.ui

import android.app.Application
import com.example.criptoapp.di.DaggerApplicationComponent

class CoinApp: Application() {

    val component by lazy {
        DaggerApplicationComponent
            .factory()
            .create(this)
    }
}