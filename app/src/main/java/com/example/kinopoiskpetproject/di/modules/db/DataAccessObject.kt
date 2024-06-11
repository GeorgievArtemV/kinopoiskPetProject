package com.example.kinopoiskpetproject.di.modules.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kinopoiskpetproject.model.Film

@Dao
interface DataAccessObject {
    @Query("Select * FROM Films")
    suspend fun getAll():List<Film>
    @Insert
    fun insert(film: Film)
    @Delete
    fun delete(film:Film)
}