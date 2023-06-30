package com.alexc.movielistapp.data.model

data class SimilarMovies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)