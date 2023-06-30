package com.alexc.movielistapp.data.model

data class Catagories(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)