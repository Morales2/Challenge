package com.morales.marvelapp.data.source.db

import com.morales.marvelapp.data.Character
import com.morales.marvelapp.data.source.CharactersDataSource
import com.morales.marvelapp.utils.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersLocalDataSource @Inject constructor(
        val appExecutors: AppExecutors,
        val tasksDao: CharactersDao
) : CharactersDataSource {
    override fun getCharacters(callback: CharactersDataSource.LoadCharactersCallback) {

    }

    override fun getCharacter(characterId: String, callback: CharactersDataSource.GetCharacterCallback) {

    }

    override fun saveCharacter(character: Character) {

    }

    override fun refreshCharacters() {

    }

    override fun deleteCharacter(characterId: String) {

    }
}