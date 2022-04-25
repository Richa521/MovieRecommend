package com.alexc.movielistapp.data.utils

object ApiConstants {

    const val API_BASE_URL = "https://imdb-api.com/en/API/"
    const val API_KEY = "k_r373dl98"

    const val API_GET_MOST_POPULAR_ROUTE = "MostPopularMovies/$API_KEY/"
    const val API_GET_IN_THEATERS_ROUTE = "InTheaters/$API_KEY/"
    const val API_GET_MOVIE_DETAILS_ROUTE = "Title/$API_KEY/"
    const val API_GET_MOVIES_BY_CATEGORIES_ROUTE = "AdvancedSearch/$API_KEY"
    const val API_GET_MOVIES_BY_SEARCH = "SearchTitle/$API_KEY/"

}