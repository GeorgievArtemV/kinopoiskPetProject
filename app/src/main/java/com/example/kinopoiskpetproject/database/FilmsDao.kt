package com.example.kinopoiskpetproject.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.kinopoiskpetproject.model.Film
import io.reactivex.Single


@Dao
interface FilmsDao {
    @Query("SELECT * FROM Film")
    fun getAll(): List<Film?>?

    @Insert
    fun insert(countries: Film?)
    @Delete
    fun delete(countries: Film?)
    @Update
    fun update(countries: Film?)
    @Query("SELECT * FROM Film WHERE kinopoiskId = :name")
    fun getByName(name: String?): Single<Film?>?
}