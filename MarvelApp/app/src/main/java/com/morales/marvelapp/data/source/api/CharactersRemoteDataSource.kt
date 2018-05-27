package com.morales.marvelapp.data.source.api

import com.morales.marvelapp.data.Character
import com.morales.marvelapp.data.source.CharactersDataSource
import javax.inject.Inject


class CharactersRemoteDataSource @Inject constructor(): CharactersDataSource {
    override fun getCharacter(characterId: String, callback: CharactersDataSource.GetCharacterCallback) {

    }

    override fun saveCharacter(character: Character) {

    }

    override fun refreshCharacters() {

    }

    override fun deleteCharacter(characterId: String) {

    }

    override fun getCharacters(callback: CharactersDataSource.LoadCharactersCallback) {

    }
}