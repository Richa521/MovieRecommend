package com.alexc.movielistapp.core.search

import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.data.models.MovieListItem

data class SearchState(
    val movies: List<Result> = emptyList(),
    val searchTerm: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
