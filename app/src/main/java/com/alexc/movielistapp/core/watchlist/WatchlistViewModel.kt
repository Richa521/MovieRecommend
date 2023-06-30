package com.alexc.movielistapp.core.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexc.movielistapp.data.model.MovieDetails
import com.alexc.movielistapp.data.models.MovieItem
import com.alexc.movielistapp.favourites.PreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val preferenceHelper: PreferencesHelper
) : ViewModel() {
    private val _watchlistMovies = MutableLiveData<List<MovieDetails>>()
    val watchlistMovies: LiveData<List<MovieDetails>> get() = _watchlistMovies

    init {
        _watchlistMovies.value = preferenceHelper.getWatchlist()
    }

    fun addToWatchlist(movie: MovieDetails) {
        val updatedWatchlist = (_watchlistMovies.value ?: emptyList()) + movie
        _watchlistMovies.value = updatedWatchlist
        preferenceHelper.saveWatchlist(updatedWatchlist)
    }

    fun removeFromWatchlist(movie:MovieDetails) {
        val updatedWatchlist = (_watchlistMovies.value ?: emptyList()).toMutableList()
        updatedWatchlist.remove(movie)
        _watchlistMovies.value = updatedWatchlist
        preferenceHelper.saveWatchlist(updatedWatchlist)
    }

    fun setWatchlist(watchlist: List<MovieDetails>) {
        _watchlistMovies.value = watchlist
    }
}
