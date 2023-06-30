package com.alexc.movielistapp.core.search.views

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.data.models.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NearestMatchedMoviesViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(NearestMatchedMoviesState())
    val state: State<NearestMatchedMoviesState> = _state

    fun addNearestMatchedMovie(movie:Result
    ) {
        val updatedMovies = state.value.nearestMatchedMovies.toMutableList()
        updatedMovies.add(movie)
        _state.value = state.value.copy(nearestMatchedMovies = updatedMovies)
    }
}

data class NearestMatchedMoviesState(
    val nearestMatchedMovies: List<Result> = emptyList()
)
