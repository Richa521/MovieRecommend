package com.alexc.movielistapp.di

import android.content.Context
import com.alexc.movielistapp.data.remote.MoviesApi
import com.alexc.movielistapp.data.utils.ApiConstants
import com.alexc.movielistapp.favourites.PreferencesHelper
import com.alexc.movielistapp.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        api: MoviesApi
    ) = MovieRepository(api)


    @Singleton
    @Provides
    fun provideMovieApi(): MoviesApi{
        return  Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).client(OkHttpClient.Builder().build()).build()
            .create(MoviesApi::class.java)
    }


    @Provides
    fun providePreferencesHelper(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }


}