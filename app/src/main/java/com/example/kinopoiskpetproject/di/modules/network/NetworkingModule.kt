package com.example.kinopoiskpetproject.di.modules.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {
    @Provides
    fun provideRetrofit():Retrofit{
        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.newBuilder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun providesFilmAPI (retrofit: Retrofit): FilmAPI {
        return retrofit.create(FilmAPI::class.java)
    }
}