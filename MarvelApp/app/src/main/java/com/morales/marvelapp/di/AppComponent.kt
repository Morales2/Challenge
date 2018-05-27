package com.morales.marvelapp.di

import android.app.Application
import com.morales.marvelapp.MarvelApplication
import com.morales.marvelapp.data.source.CharactersRepository
import com.morales.marvelapp.data.source.CharactersRepositoryModule
import com.morales.marvelapp.ui.characters.CharactersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    CharactersRepositoryModule::class,
    ApplicationModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    NetworkModule::class
])
interface AppComponent : AndroidInjector<MarvelApplication> {

    fun getCharactersRepository(): CharactersRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}