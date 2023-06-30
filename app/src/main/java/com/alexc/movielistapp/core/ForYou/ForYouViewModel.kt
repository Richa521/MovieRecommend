package com.alexc.movielistapp.core.ForYou

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.core.search.SearchState
import com.alexc.movielistapp.core.search.SearchViewModel
import com.alexc.movielistapp.data.models.MovieItem
import com.alexc.movielistapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {


    var state by mutableStateOf(SearchState())

    private var searchJob: Job? = null

    fun onSearchBackend(searchString: String) {
        state = state.copy(searchTerm = searchString)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            println("onSearchBacked has successfully")
            searchMovieBackend()

        }
    }


    private suspend fun searchMovieBackend() {
        state = state.copy(isLoading = true)
        println(state.searchTerm)
        val result = repository.getMoviesBySearch(searchTerm = state.searchTerm)
        when (result) {
            is Resource.Success -> {
                state = state.copy(
                    isLoading = false,
                    isError = false,
                    movies = result.data ?: emptyList()
                )
                println("Sucess")
            }

            is Resource.Error -> {
                state = state.copy(
                    isLoading = false,
                    isError = true
                )
                println("Errror")
            }
        }
    }
}
