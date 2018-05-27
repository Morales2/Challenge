package com.morales.marvelapp.ui.characters

import com.morales.marvelapp.ui.base.BasePresenter
import com.morales.marvelapp.ui.base.BaseView
import com.morales.marvelapp.data.Character

interface CharactersContract {

    interface View : BaseView<Presenter> {

        fun setLoadingIndicator(active : Boolean)

        fun showAllCharacters(characters: List<Character>)

        fun showFavouriteCharacters(characters: List<Character>)

        fun showCharacterDetailsUi(characterId: String)

        fun showLoadingCharactersError()

    }

    interface Presenter : BasePresenter {

        fun loadAllCharacters()

        fun loadFavouriteCharacters()

        fun openCharacterDetails()

    }
}