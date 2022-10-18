package com.example.criptoapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface ApplicationComponentFactory{

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}