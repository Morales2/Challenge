package com.morales.marvelapp

import android.support.annotation.VisibleForTesting
import com.morales.marvelapp.data.source.CharactersRepository
import com.morales.marvelapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class MarvelApplication : DaggerApplication() {
    @Inject
    lateinit var charactersRepository: CharactersRepository

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    @VisibleForTesting
    fun getTasksRepository(): CharactersRepository {
        return charactersRepository
    }
}