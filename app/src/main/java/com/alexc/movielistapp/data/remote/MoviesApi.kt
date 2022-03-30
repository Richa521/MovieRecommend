package com.alexc.movielistapp.data.remote

import com.alexc.movielistapp.data.models.RequestItemsList
import com.alexc.movielistapp.data.models.MovieItem
import com.alexc.movielistapp.data.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {

    @GET(ApiConstants.API_GET_MOST_POPULAR_ROUTE)
    suspend fun getMostPopularMovies(): RequestItemsList

    @GET(ApiConstants.API_GET_COMING_SOON_ROUTE)
    suspend fun getComingSoonMovies(): RequestItemsList

    @GET(ApiConstants.API_GET_MOVIE_DETAILS_ROUTE + "/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String
    ): MovieItem
}