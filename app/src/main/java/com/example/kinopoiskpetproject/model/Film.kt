package com.example.kinopoiskpetproject.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Films")
data class Film(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "kinoposiId") val kinopoiskId:Int,
    @ColumnInfo(name = "nameRu") val nameRu: String?,
    @ColumnInfo(name = "nameEn") val nameEn: String?,
    @ColumnInfo(name = "nameOriginal") val nameOriginal: String?,
    //@ColumnInfo(name = "countries") val countries: ArrayList<Country>?,
    //@ColumnInfo(name = "genres") val genres: ArrayList<Genre>?,
    @ColumnInfo(name = "year") val year: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "posterUrl") val posterUrl: String?,
    @ColumnInfo(name = "posterUrlPreview") val posterUrlPreview: String?,
    @ColumnInfo(name = "ratingKinopoisk") val ratingKinopoisk:Double = 0.0,
    @ColumnInfo(name = "ratingImbd") val ratingImbd:Double = 0.0
    ) : Parcelable

@Parcelize
data class Country (val country: String?) : Parcelable
@Parcelize
data class Genre (val genre: String?) : Parcelable

