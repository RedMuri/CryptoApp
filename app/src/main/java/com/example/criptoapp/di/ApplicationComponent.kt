package com.example.criptoapp.di

import android.app.Application
import com.example.criptoapp.data.workers.WorkerModule
import com.example.criptoapp.ui.CoinApp
import com.example.criptoapp.ui.screens.CoinInfoFragment
import com.example.criptoapp.ui.screens.CoinListFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class, WorkerModule::class])
interface ApplicationComponent {

    fun inject(infoFragment: CoinInfoFragment)

    fun inject(listFragment: CoinListFragment)

    fun inject(application: CoinApp)

    @Component.Factory
    interface ApplicationComponentFactory{

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}