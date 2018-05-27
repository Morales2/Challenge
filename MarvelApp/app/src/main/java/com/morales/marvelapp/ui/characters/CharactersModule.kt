package com.morales.marvelapp.ui.characters

import com.morales.marvelapp.di.ActivityScoped
import com.morales.marvelapp.di.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CharactersModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun charactersFragment(): CharactersFragment

    @ActivityScoped
    @Binds
    internal abstract fun charactersPresenter(charactersPresenter: CharactersPresenter): CharactersContract.Presenter

}