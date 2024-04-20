package com.example.kinopoiskpetproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Film(
    @PrimaryKey(autoGenerate = true)
    val kinopoiskId:Int,
    val nameRu: String?,
    val nameEn: String?,
    val nameOriginal: String?,
    val countries: ArrayList<Country>?,
    val genres: ArrayList<Genre>?,
    val year: String?,
    val type: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val ratingKinopoisk:Double = 0.0,
    val ratingImbd:Double = 0.0
    )
data class Country (val country: String?)
data class Genre (val genre: String?)

