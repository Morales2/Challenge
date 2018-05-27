package com.morales.marvelapp.data.source

import com.morales.marvelapp.data.Character

interface CharactersDataSource {

    interface LoadCharactersCallback {

        fun onCharactersLoaded(characters: List<Character>)

        fun onDataNotAvailable()
    }

    interface GetCharacterCallback {

        fun onCharacterLoaded(character: Character)

        fun onDataNotAvailable()
    }

    fun getCharacters(callback: LoadCharactersCallback)

    fun getCharacter(characterId: String, callback: GetCharacterCallback)

    fun saveCharacter(character: Character)

    fun refreshCharacters()

    fun deleteCharacter(characterId: String)
}