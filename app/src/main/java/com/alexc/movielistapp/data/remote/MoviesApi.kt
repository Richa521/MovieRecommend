package com.alexc.movielistapp.data.remote

import com.alexc.movielistapp.data.models.RequestItemsList
import com.alexc.movielistapp.data.models.MovieItem
import com.alexc.movielistapp.data.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET(ApiConstants.API_GET_MOST_POPULAR_ROUTE)
    suspend fun getMostPopularMovies(): RequestItemsList

    @GET(ApiConstants.API_GET_IN_THEATERS_ROUTE)
    suspend fun getInTheatersMovies(): RequestItemsList

    @GET(ApiConstants.API_GET_MOVIE_DETAILS_ROUTE + "{movieId}" + "/Images")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String
    ): MovieItem

    @GET(ApiConstants.API_GET_MOVIES_BY_CATEGORIES_ROUTE)
    suspend fun getMovieByCategory(
        @Query("title_type") type: String = "feature",
        @Query("genres") category: String
    ): RequestItemsList

    @GET(ApiConstants.API_GET_MOVIES_BY_SEARCH + "{searchTerm}")
    suspend fun getMovieBySearch(
        @Path("searchTerm") searchTerm: String
    ): RequestItemsList
}