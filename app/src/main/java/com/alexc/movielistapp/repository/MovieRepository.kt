package com.alexc.movielistapp.repository

import android.util.Log
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.data.models.MovieItem
import com.alexc.movielistapp.data.models.MovieListItem
import com.alexc.movielistapp.data.remote.MoviesApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MovieRepository @Inject constructor(
    private val api: MoviesApi
) {

    suspend fun getMostPopularMovies(): Resource<List<MovieListItem>> {
        val response = try {
               api.getMostPopularMovies()
           } catch (e: Exception) {
               return Resource.Error("An error occurred while obtaining most popular movies")
           }

        return Resource.Success(response.items)
    }

    suspend fun getInTheatersMovies(): Resource<List<MovieListItem>> {
        val response = try {
            api.getInTheatersMovies()
        } catch (e: Exception) {
            return Resource.Error("An error occurred while obtaining in theaters movies")
        }

        return Resource.Success(response.items)
    }

    suspend fun getMovieDetails(movieId: String): Resource<MovieItem> {
        val response = try {
            api.getMovieDetails(movieId)
        } catch (e: Exception) {
            return Resource.Error(e.stackTraceToString())
        }

        return Resource.Success(response)
    }

    suspend fun getMoviesByCategory(category: String): Resource<List<MovieListItem>> {
        val response = try {
            api.getMovieByCategory(category = category)
        } catch (e: Exception) {
            return Resource.Error(e.stackTraceToString())
        }

        return Resource.Success(response.results)
    }

    suspend fun getMoviesBySearch(searchTerm: String): Resource<List<MovieListItem>> {
        val response = try {
            api.getMovieBySearch(searchTerm = searchTerm)
        } catch (e: Exception) {
            return Resource.Error(e.stackTraceToString())
        }

        return Resource.Success(response.results)
    }
}