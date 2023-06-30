package com.alexc.movielistapp.data.utils

object ApiConstants {
    const val API_BASE_URL_TDB="https://api.themoviedb.org/3/"
    const val API_BASE_URL = "https://imdb-api.com/en/API/"
    const val API_KEY = "k_u6m3v844"

    const val API_GET_MOST_POPULAR_ROUTE_TDB="movie/popular?api_key=1a481fb3b0ece19bc698134b08404e59"
    const val API_GET_MOST_POPULAR_ROUTE = "MostPopularMovies/$API_KEY/"
    const val API_GET_IN_THEATERS_ROUTE_TDB=" movie/now_playing?api_key=1a481fb3b0ece19bc698134b08404e59"
    const val API_GET_IN_THEATERS_ROUTE = "InTheaters/$API_KEY/"
    const val API_GET_MOVIE_DETAILS_ROUTE = "Title/$API_KEY/"
    const val API_GET_MOVIES_BY_CATEGORIES_ROUTE = "search/collection?api_key=1a481fb3b0ece19bc698134b08404e59"
    const val API_GET_MOVIES_BY_SEARCH = "search/movie?api_key=1a481fb3b0ece19bc698134b08404e59"

}