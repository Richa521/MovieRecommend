package com.alexc.movielistapp.core.search


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.core.ForYou.ForYouViewModel
import com.alexc.movielistapp.data.models.MovieItem
import com.alexc.movielistapp.data.models.MovieListItem
import com.alexc.movielistapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    var state by mutableStateOf(SearchState())
    var nearestMovieList by mutableStateOf<List<String>>(emptyList())
    var nearestMovies by mutableStateOf<List<String>>(emptyList())

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
                updateNearestMovieList()
            }

            is Resource.Error -> {
                state = state.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

    fun updateNearestMovieList() {
        val searchResultMovies = state.movies


        val list = listOf("Phantom", "Flash", "Bahubali", "Spiderman", "Padmavati")
        val nearestMovies = mutableListOf<String>()

        for (movieName in list) {
            val nearestMovie = searchResultMovies.find { movie -> movie.title == movieName }
            nearestMovie?.let {
                nearestMovies.add(it.title)
            }
        }


        nearestMovieList = nearestMovies
    }
}
