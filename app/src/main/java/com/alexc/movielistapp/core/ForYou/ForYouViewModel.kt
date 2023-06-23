package com.alexc.movielistapp.core.ForYou

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.core.search.SearchViewModel
import com.alexc.movielistapp.core.ForYou.Movie
import com.alexc.movielistapp.data.models.MovieItem
import com.alexc.movielistapp.data.models.MovieListItem
import com.alexc.movielistapp.favourites.PreferencesHelper
import com.alexc.movielistapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _nearestMovies = MutableLiveData<List<MovieItem>>()
    val nearestMovies: LiveData<List<MovieItem>> get() = _nearestMovies





}
