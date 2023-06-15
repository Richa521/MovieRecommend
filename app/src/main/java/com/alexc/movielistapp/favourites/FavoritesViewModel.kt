package com.alexc.movielistapp.favourites

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexc.movielistapp.data.models.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val preferenceHelper: PreferencesHelper
) : ViewModel() {
    private val _favoriteMovies = MutableLiveData<List<MovieItem>>()
    val favoriteMovies: LiveData<List<MovieItem>> get() = _favoriteMovies

    init {
        _favoriteMovies.value = preferenceHelper.getFavorites()
    }

    fun addToFavorites(movie: MovieItem) {
        val updatedFavorites = (_favoriteMovies.value ?: emptyList()) + movie
        _favoriteMovies.value = updatedFavorites
        preferenceHelper.saveFavorites(updatedFavorites)
    }
    fun setFavorites(favorites: List<MovieItem>) {
        _favoriteMovies.value = favorites
    }
}