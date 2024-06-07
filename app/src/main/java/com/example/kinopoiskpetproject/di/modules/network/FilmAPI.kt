package com.example.kinopoiskpetproject.di.modules.network

import com.example.kinopoiskpetproject.model.FilmDetails
import com.example.kinopoiskpetproject.model.FilmList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface FilmAPI {
    @Headers("X-API-KEY:" + " e30ffed0-76ab-4dd6-b41f-4c9da2b2735b", "accept: application/json")
    @GET("api/v2.2/films/collections?type=TOP_250_MOVIES&page=1")
    suspend fun getFilms(): Response<FilmList>
    @Headers("X-API-KEY:" + " e30ffed0-76ab-4dd6-b41f-4c9da2b2735b", "accept: application/json")
    @GET("api/v2.2/films/{id}")
    suspend fun getFilmDetails(@Path("id") filmId: Int): Response<FilmDetails?>?
}