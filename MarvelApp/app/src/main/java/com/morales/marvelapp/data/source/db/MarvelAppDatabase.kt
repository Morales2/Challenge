package com.morales.marvelapp.data.source.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.morales.marvelapp.data.Character

/**
 * The Room Database that contains the character table.
 */
@Database(entities = [Character::class], version = 1)
abstract class MarvelAppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharactersDao

    companion object {

        private var INSTANCE: MarvelAppDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): MarvelAppDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MarvelAppDatabase::class.java, "characters.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }

}