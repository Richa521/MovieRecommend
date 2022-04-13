package com.alexc.movielistapp.core.movielist

import androidx.lifecycle.ViewModel
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.data.models.MovieListItem
import com.alexc.movielistapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    suspend fun loadMovies(category: String): Resource<List<MovieListItem>> =
        repository.getMoviesByCategory(category = category)
}