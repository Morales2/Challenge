package com.morales.marvelapp.data.source.api

import com.morales.marvelapp.data.source.api.CharacterDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    //http://gateway.marvel.com:443/v1/public/characters?name=Iron%20Man&apikey=PUBLIC_API_KEY&hash=HASH&ts=TIMESTAMP
    @GET("/v1/public/charactersItem")
    fun getCharacters(@Query("limit") limit: Int,
                      @Query("offset") offset: Int,
                      @Query("orderBy") orderBy: String,
                      @Query("apikey") apiKey: String,
                      @Query("hash") hash: String,
                      @Query("ts") timeStamp: Long = System.currentTimeMillis() / 1000)
            : Call<CharacterDataWrapper>
}