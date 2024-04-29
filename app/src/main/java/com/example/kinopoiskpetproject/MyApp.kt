package com.example.kinopoiskpetproject

import android.app.Application
import com.example.kinopoiskpetproject.ui.utils.FilmAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        configureRetrofit()
    }
    fun configureRetrofit():FilmAPI {
        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.newBuilder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val filmAPI = retrofit.create(FilmAPI::class.java)
        return filmAPI
    }
}