package com.alexc.movielistapp.core.moviedetails

import androidx.lifecycle.ViewModel
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.data.model.MovieDetails
import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    suspend fun loadMovie(movieId: String): Resource<MovieDetails> =
        repository.getMovieDetails(movieId)

    suspend fun SimilarMovie(movieId: String): Resource<List<Result>> =
        repository.getMoviesBySimilar(movieId)



}