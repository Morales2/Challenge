package com.morales.marvelapp.ui.characters

import com.morales.marvelapp.data.source.CharactersRepository
import com.morales.marvelapp.di.ActivityScoped
import org.jetbrains.annotations.Nullable
import javax.inject.Inject

@ActivityScoped
class CharactersPresenter @Inject constructor(val charactersRepository: CharactersRepository)
    : CharactersContract.Presenter {

    @Nullable
    lateinit var mView : CharactersContract.View

    override fun loadAllCharacters() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadFavouriteCharacters() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openCharacterDetails() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}