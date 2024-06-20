package com.example.kinopoiskpetproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kinopoiskpetproject.model.Film


@Database(entities = [Film::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
}