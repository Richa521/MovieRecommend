package com.alexc.movielistapp.favourites

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexc.movielistapp.data.model.MovieDetails
import com.alexc.movielistapp.data.models.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val preferenceHelper: PreferencesHelper
) : ViewModel() {
    private val _favoriteMovies = MutableLiveData<List<MovieDetails>>()
    val favoriteMovies: LiveData<List<MovieDetails>> get() = _favoriteMovies

    init {
        _favoriteMovies.value = preferenceHelper.getFavorites()
    }

    fun addToFavorites(movie: MovieDetails) {
        val updatedFavorites = (_favoriteMovies.value ?: emptyList()) + movie
        _favoriteMovies.value = updatedFavorites
        preferenceHelper.saveFavorites(updatedFavorites)
    }
    fun setFavorites(favorites: List<MovieDetails>) {
        _favoriteMovies.value = favorites
    }
}