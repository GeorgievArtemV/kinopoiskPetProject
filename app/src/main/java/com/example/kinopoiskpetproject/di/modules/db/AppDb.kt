package com.example.kinopoiskpetproject.di.modules.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kinopoiskpetproject.model.Film

@Database(entities = [Film::class], version = 1)
abstract class AppDb: RoomDatabase() {
    abstract fun filmDao(): DataAccessObject
}