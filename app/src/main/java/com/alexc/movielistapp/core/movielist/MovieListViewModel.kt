package com.alexc.movielistapp.core.movielist

import androidx.lifecycle.ViewModel
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.data.model.ResultX
import com.alexc.movielistapp.data.models.MovieListItem
import com.alexc.movielistapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {


    suspend fun loadMovies(category: String): Resource<List<Result>> =
        repository.getMoviesByCategory(category = category)
}