package com.alexc.movielistapp.data.model

data class CatogoriesMovies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)