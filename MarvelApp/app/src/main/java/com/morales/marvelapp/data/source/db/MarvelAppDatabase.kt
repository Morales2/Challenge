package com.morales.marvelapp.data.source.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.morales.marvelapp.data.Character

/**
 * The Room Database that contains the character table.
 */
@Database(entities = [Character::class], version = 1, exportSchema = false)
abstract class MarvelAppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharactersDao
}