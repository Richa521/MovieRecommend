package com.alexc.movielistapp.core.homescreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.data.models.MovieListItem
import com.alexc.movielistapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val mostPopularMoviesList = mutableStateOf<List<MovieListItem>>(listOf())
    val mostPopularIsLoading = mutableStateOf(false)
    val mostPopularIsError = mutableStateOf(false)

    init {
        loadMostPopular()
    }


    private fun loadMostPopular() {
        mostPopularIsLoading.value = true
        viewModelScope.launch {
            val result = repository.getMostPopularMovies()

            mostPopularIsLoading.value = false
            when (result) {
                is Resource.Success -> {
                    mostPopularMoviesList.value = result.data!!
                    if(result.data!!.isEmpty()){
                        val list = arrayListOf<MovieListItem>()
                        // FIXME
                        list.add(MovieListItem(id = "tt1877830", title = "Batman", fullTitle = "Batman", image = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQt8yNufq2Jp9-D4BrCYYW5Y_jm8HXRE6NDczXQbTjV-5DMBS4o"))
                        list.add(MovieListItem(id = "tt10872600", title = "Spider-Man: No way home", fullTitle = "Spider-Man: No way home", image = "https://sportshub.cbsistatic.com/i/2022/01/21/6c422820-0c68-41e2-8496-ccc76599f26a/spider-man-no-way-home-multiverse-poster.jpg?auto=webp&width=928&height=1374&crop=0.675:1,smart"))
                        list.add(MovieListItem(id = "tt0087182", title = "Dune", fullTitle = "Dune", image = "https://mb.web.sapo.io/94689138819576d05725aea934e3421eb30cd805.jpg"))

                        mostPopularMoviesList.value = list
                    }

                    mostPopularIsError.value = false
                }
                is Resource.Error -> {
                    mostPopularIsError.value = true
                }
            }
        }
    }
}