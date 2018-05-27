package com.morales.marvelapp.data.source.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.morales.marvelapp.data.Character

@Dao
interface CharactersDao {
    @Query("SELECT * FROM Favorites")
    fun getFavourites(): List<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: Character)

    @Query("DELETE FROM Favorites WHERE id = :favoriteId")
    fun deleteFavoriteById(favoriteId: String): Int
}