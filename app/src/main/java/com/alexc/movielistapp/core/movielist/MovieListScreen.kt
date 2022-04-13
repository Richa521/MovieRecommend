package com.alexc.movielistapp.core.movielist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.data.models.MovieListItem
import com.alexc.movielistapp.ui.theme.OpenSans
import com.google.accompanist.coil.rememberCoilPainter


@Composable
fun MovieListScreen(
    navController: NavController,
    category: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = category.uppercase(),
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            },
            backgroundColor = MaterialTheme.colors.surface,
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIos,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = "back")
                }
            }
        )
        MovieList(navController = navController, category = category)
    }
}


@Composable
fun MovieList(
    navController: NavController,
    category: String,
    viewModel: MovieListViewModel = hiltViewModel()
) {

    val moviesResource =
        produceState<Resource<List<MovieListItem>>>(initialValue = Resource.Loading()) {
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
    movies: List<MovieListItem>,
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


@Composable
fun MovieListCard(
    movieItem: MovieListItem,
    navController: NavController,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .height(200.dp)
            .clickable {
                navController.navigate(
                    "movie_details_screen/${movieItem.id}"
                )
            }
            .padding(5.dp),
    ) {
        val color = MaterialTheme.colors.primary
        Image(
            painter = rememberCoilPainter(request = movieItem.image),
            contentDescription = movieItem.title,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxSize()
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, color),
                        startY = size.height / 10,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                },
            contentScale = ContentScale.Crop,
        )

        Text(
            movieItem.title,
            fontFamily = OpenSans,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(5.dp)
        )
    }
}