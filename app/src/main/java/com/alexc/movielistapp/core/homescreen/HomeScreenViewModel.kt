package com.alexc.movielistapp.core.homescreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val mostPopularMoviesList = mutableStateOf<List<Result>>(listOf())
    val mostPopularIsLoading = mutableStateOf(false)
    val mostPopularIsError = mutableStateOf(false)

    val inTheatersMovies = mutableStateOf<List<Result>>(listOf())
    val inTheatersIsLoading = mutableStateOf(false)

    init {
        loadMostPopular()
        loadInTheaters()
    }


    private fun loadMostPopular() {
        mostPopularIsLoading.value = true
        viewModelScope.launch {
            val result = repository.getMostPopularMovies()
            mostPopularIsLoading.value = false
            when (result) {
                is Resource.Success -> {
                    mostPopularMoviesList.value = result.data!!

                }

                is Resource.Error -> {

                }
            }
        }
    }

    private fun loadInTheaters() {
        inTheatersIsLoading.value = true
        viewModelScope.launch {
            val result = repository.getInTheatersMovies()
            inTheatersIsLoading.value = false
            when (result) {
                is Resource.Success -> {
                    inTheatersMovies.value = result.data!!
                }

                is Resource.Error -> {
                }
            }
        }
    }
}