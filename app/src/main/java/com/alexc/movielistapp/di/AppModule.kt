package com.alexc.movielistapp.di

import com.alexc.movielistapp.data.remote.MoviesApi
import com.alexc.movielistapp.data.utils.ApiConstants
import com.alexc.movielistapp.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConstants.API_BASE_URL)
            .build()
            .create(MoviesApi::class.java)
    }


}