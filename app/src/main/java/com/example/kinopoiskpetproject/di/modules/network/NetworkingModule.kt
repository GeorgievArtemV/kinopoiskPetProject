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
    fun provideClient(): OkHttpClient {
        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return client.newBuilder().addInterceptor(interceptor).build()
    }
    @Provides
    fun provideRetrofit(client: OkHttpClient):Retrofit{
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