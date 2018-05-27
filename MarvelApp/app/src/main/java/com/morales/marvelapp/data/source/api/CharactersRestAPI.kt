package com.morales.marvelapp.data.source.api

import com.morales.marvelapp.utils.Constants
import com.morales.marvelapp.utils.extensions.md5
import retrofit2.Call
import javax.inject.Inject

class CharactersRestAPI @Inject constructor(private val marvelApi: MarvelApi) {
    fun getCharacters(limit: Int, offset: Int): Call<CharacterDataWrapper> {
        val orderBy = "name"
        val apiKey = Constants.PUBLIC_KEY
        val timeStamp = System.currentTimeMillis() / 1000
        val hash = "$timeStamp${Constants.PRIVATE_KEY}${Constants.PUBLIC_KEY}"
        return marvelApi.getCharacters(limit, offset, orderBy, apiKey, hash.md5(), timeStamp)
    }
}