package com.morales.marvelapp.di

import com.morales.marvelapp.ui.characters.CharactersActivity
import com.morales.marvelapp.ui.characters.CharactersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [(CharactersModule::class)])
    abstract fun charactersActivity(): CharactersActivity
}