package com.morales.marvelapp.data.source

import com.morales.marvelapp.data.Character
import com.morales.marvelapp.data.source.api.CharactersRemoteDataSource
import com.morales.marvelapp.data.source.db.CharactersLocalDataSource
import javax.inject.Inject

class CharactersRepository @Inject constructor(
        val charactersRemoteDataSource: CharactersRemoteDataSource,
        val charactersLocalDataSource: CharactersLocalDataSource
) : CharactersDataSource {
    override fun getCharacters(callback: CharactersDataSource.LoadCharactersCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCharacter(characterId: String, callback: CharactersDataSource.GetCharacterCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveCharacter(character: Character) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshCharacters() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCharacter(characterId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}