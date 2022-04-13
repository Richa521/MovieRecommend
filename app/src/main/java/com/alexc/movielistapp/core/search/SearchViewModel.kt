package com.alexc.movielistapp.core.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    var state by mutableStateOf(SearchState())

    private var searchJob: Job? = null

    fun onSearch(searchString: String) {
        state = state.copy(searchTerm = searchString)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            searchMovie()
        }
    }

    private suspend fun searchMovie() {
        state = state.copy(isLoading = true)
        val result = repository.getMoviesBySearch(searchTerm = state.searchTerm)
        when (result) {
            is Resource.Success -> {
                state = state.copy(
                    isLoading = false,
                    isError = false,
                    movies = result.data ?: emptyList()
                )
            }

            is Resource.Error -> {
                state = state.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }
}