package com.alexc.movielistapp.core.movielist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.data.model.ResultX
import com.alexc.movielistapp.data.models.MovieListItem

@Composable
fun MovieList(
    navController: NavController,
    category: String,
    viewModel: MovieListViewModel = hiltViewModel()
) {

    val moviesResource =
        produceState<Resource<List<Result>>>(initialValue = Resource.Loading()) {
            value = viewModel.loadMovies(category = category)
        }.value

    val movieList = moviesResource.data

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        if (!movieList.isNullOrEmpty()) {
            val itemCount = if (movieList!!.size % 2 == 0) {
                movieList!!.size / 2
            } else {
                movieList!!.size / 2 + 1
            }
            items(itemCount) {
                MovieListRow(rowIndex = it, movies = movieList, navController = navController)
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (moviesResource is Resource.Loading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)

        } else if (moviesResource is Resource.Error) {
            // TODO
        }
    }
}


@Composable
fun MovieListRow(
    rowIndex: Int,
    movies: List<Result>,
    navController: NavController
) {
    Column {
        Row {
            MovieListCard(
                movieItem = movies[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (movies.size >= rowIndex * 2 + 2) {
                MovieListCard(
                    movieItem = movies[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
