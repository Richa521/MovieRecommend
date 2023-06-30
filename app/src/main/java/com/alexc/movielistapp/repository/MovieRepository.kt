package com.alexc.movielistapp.repository

import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.data.model.MovieDetails
import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.data.model.ResultX
import com.alexc.movielistapp.data.models.MovieItem
import com.alexc.movielistapp.data.models.MovieListItem
import com.alexc.movielistapp.data.remote.MoviesApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MovieRepository @Inject constructor(
    private val api: MoviesApi
) {

    suspend fun getMostPopularMovies(): Resource<List<Result>> {
        val response = try {
               api.getMostPopularMovies()
           } catch (e: Exception) {
               return Resource.Error("An error occurred while obtaining most popular movies")
           }

        return Resource.Success(response.results)
    }

    suspend fun getInTheatersMovies(): Resource<List<Result>> {
        val response = try {
            api.getInTheatersMovies()
        } catch (e: Exception) {
            return Resource.Error("An error occurred while obtaining in theaters movies")
        }

        return Resource.Success(response.results)
    }

    suspend fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = try {
            api.getMovieDetails(movieId)
        } catch (e: Exception) {
            return Resource.Error(e.stackTraceToString())
        }

        return Resource.Success(response)
    }

    suspend fun getMoviesByCategory(category: String): Resource<List<Result>> {
        val response = try {
            api.getMovieByCategory(category = category)
        } catch (e: Exception) {
            return Resource.Error(e.stackTraceToString())
        }

        return Resource.Success(response.results)
    }

    suspend fun getMoviesBySearch(searchTerm: String): Resource<List<Result>> {
        val response = try {
            api.getMovieBySearch(searchTerm = searchTerm)
        } catch (e: Exception) {
            return Resource.Error(e.stackTraceToString())
        }

        return Resource.Success(response.results)
    }
    suspend fun getMoviesBySimilar(movieId: String): Resource<List<Result>> {
        val response = try {
            api.SimilarMovies(movieId = movieId)
        } catch (e: Exception) {
            return Resource.Error(e.stackTraceToString())
        }

        return Resource.Success(response.results)
    }


//    suspend fun getDefaultMovies(): Resource<List<MovieListItem>> {
//        val defaultCategory = "popular" // You can use any default category here
//       // return getMoviesByCategory(defaultCategory)
//    }




}