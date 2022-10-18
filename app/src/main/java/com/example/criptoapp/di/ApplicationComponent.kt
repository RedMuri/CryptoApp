package com.example.criptoapp.di

import android.app.Application
import com.example.criptoapp.ui.screens.CoinInfoFragment
import com.example.criptoapp.ui.screens.CoinListFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(infoFragment: CoinInfoFragment)

    fun inject(listFragment: CoinListFragment)

    @Component.Factory
    interface ApplicationComponentFactory{

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}