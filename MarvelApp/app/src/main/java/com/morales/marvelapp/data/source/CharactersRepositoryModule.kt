package com.morales.marvelapp.data.source

import android.app.Application
import android.arch.persistence.room.Room
import com.morales.marvelapp.data.source.api.CharactersRemoteDataSource
import com.morales.marvelapp.data.source.api.CharactersRestAPI
import com.morales.marvelapp.data.source.api.MarvelApi
import com.morales.marvelapp.data.source.db.CharactersDao
import com.morales.marvelapp.data.source.db.CharactersLocalDataSource
import com.morales.marvelapp.data.source.db.MarvelAppDatabase
import com.morales.marvelapp.utils.AppExecutors
import com.morales.marvelapp.utils.AppExecutors.Companion.THREAD_COUNT
import com.morales.marvelapp.utils.DiskIOThreadExecutor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.util.concurrent.Executors
import javax.inject.Singleton


@Module
class CharactersRepositoryModule {

    @Singleton
    @Provides
    @Local
    fun provideCharactersLocalDataSource(
            executors: AppExecutors,
            dao: CharactersDao
    ): CharactersDataSource = CharactersLocalDataSource(executors, dao)

    @Singleton
    @Provides
    @Remote
    fun provideCharactersRemoteDataSource(): CharactersDataSource = CharactersRemoteDataSource()

    @Singleton
    @Provides
    fun provideCharactersRestAPI(marvelApi: MarvelApi): CharactersRestAPI = CharactersRestAPI(marvelApi)

    @Singleton
    @Provides
    fun provideMarvelAPI(retrofit: Retrofit): MarvelApi = retrofit.create(MarvelApi::class.java)

    @Singleton
    @Provides
    fun provideDb(context: Application): MarvelAppDatabase =
            Room.databaseBuilder(
                    context.applicationContext,
                    MarvelAppDatabase::class.java,
                    "MarvelApp.db"
            ).build()

    @Singleton
    @Provides
    fun provideCharactersDao(db: MarvelAppDatabase): CharactersDao = db.characterDao()

    @Singleton
    @Provides
    fun provideAppExecutors(): AppExecutors = AppExecutors(
            DiskIOThreadExecutor(),
            Executors.newFixedThreadPool(THREAD_COUNT),
            AppExecutors.Companion.MainThreadExecutor()
    )
}