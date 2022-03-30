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

    suspend fun getComingSoonMovies(): Resource<List<MovieListItem>> {
        val response = try {
            api.getComingSoonMovies()
        } catch (e: Exception) {
            Log.e("boas", e.toString())
            return Resource.Error("An error occurred while obtaining coming soon movies")
        }

        return Resource.Success(response.items)
    }

    suspend fun getMovieDetails(movieId: String): Resource<MovieItem> {
        val response = try {
            api.getMovieDetails(movieId)
        } catch (e: Exception) {
            return Resource.Error("An error occurred while obtaining movie details")
        }

        return Resource.Success(response)
    }
}