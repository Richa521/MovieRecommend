package com.alexc.movielistapp.data.remote

import com.alexc.movielistapp.data.model.*
import com.alexc.movielistapp.data.models.RequestItemsList
import com.alexc.movielistapp.data.models.MovieItem
import com.alexc.movielistapp.data.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET(ApiConstants.API_GET_MOST_POPULAR_ROUTE_TDB)
    suspend fun getMostPopularMovies(): PopularMovies

    @GET(ApiConstants.API_GET_IN_THEATERS_ROUTE_TDB)
    suspend fun getInTheatersMovies(): NowPlaying

    @GET("movie/{movie_id}?api_key=1a481fb3b0ece19bc698134b08404e59")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String
    ): MovieDetails

    @GET("movie/{movie_id}/similar?api_key=1a481fb3b0ece19bc698134b08404e59")
    suspend fun getMovieByCategory(
        @Path("movie_id") category: String
    ): CatogoriesMovies

    @GET(ApiConstants.API_GET_MOVIES_BY_SEARCH)
    suspend fun getMovieBySearch(
        @Query("query") searchTerm: String
    ): SearchMovies

    @GET("movie/{movie_id}/recommendations?api_key=1a481fb3b0ece19bc698134b08404e59")
    suspend fun SimilarMovies(
        @Path("movie_id") movieId: String
    ): SimilarMovies


}