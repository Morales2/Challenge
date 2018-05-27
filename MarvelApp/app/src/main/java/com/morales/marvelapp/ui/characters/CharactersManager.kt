package com.morales.marvelapp.ui.characters

import com.morales.marvelapp.data.source.MarvelCharacterItem
import com.morales.marvelapp.data.source.MarvelPage
import com.morales.marvelapp.data.source.api.CharactersRestAPI
import com.morales.marvelapp.utils.Constants
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersManager @Inject constructor(private val api: CharactersRestAPI) {
    fun getCharacters(offset: Int = 0, limit: Int = Constants.PAGE_SIZE): Observable<MarvelPage> {
        return Observable.create { subscriber ->
            val callResponse = api.getCharacters(limit, offset)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val dataResponse = response.body()?.data
                val characters = dataResponse?.results?.map {
                    MarvelCharacterItem(it.id, it.name, it.description,
                            it.thumbnail, false)
                }
                if (characters != null) {
                    val marvelCharacters = MarvelPage(
                            dataResponse.offset ?: 0,
                            dataResponse.limit ?: Constants.PAGE_SIZE,
                            characters)

                    subscriber.onNext(marvelCharacters)
                }
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}