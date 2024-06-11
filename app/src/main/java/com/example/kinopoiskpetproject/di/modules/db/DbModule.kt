package com.example.kinopoiskpetproject.di.modules.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    fun provideDB(@ApplicationContext app: Context):AppDb {
        return Room.databaseBuilder(app, AppDb::class.java, "Films").build()
    }
    @Provides
    fun provideDAO(db :AppDb):DataAccessObject {
        return db.filmDao()
    }
}