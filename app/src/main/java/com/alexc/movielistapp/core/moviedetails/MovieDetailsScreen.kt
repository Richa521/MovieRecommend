package com.alexc.movielistapp.core.moviedetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alexc.movielistapp.common.Resource
import com.alexc.movielistapp.core.moviedetails.views.MovieDescription
import com.alexc.movielistapp.core.watchlist.WatchlistViewModel
import com.alexc.movielistapp.data.models.*
import com.alexc.movielistapp.favourites.FavoritesViewModel
import com.alexc.movielistapp.repository.MovieRepository
import com.google.accompanist.coil.rememberCoilPainter


@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieId: String,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    watchlistViewModel: WatchlistViewModel = hiltViewModel()

) {

    val movieInfo = produceState<Resource<MovieItem>>(initialValue = Resource.Loading()) {
        value = viewModel.loadMovie(movieId)
    }.value

    Box(modifier = Modifier.fillMaxSize()) {
        MovieDetailStateWrapper(
            navController = navController,
            movieItem = movieInfo,
            favoritesViewModel = favoritesViewModel,
            watchlistViewModel = watchlistViewModel,
            loadingModifier = Modifier.align(Alignment.Center)
        )

    }


}


@Composable
fun MovieDetail(
    navController: NavController,
    movie: MovieItem,
    favoritesViewModel: FavoritesViewModel,
    watchlistViewModel: WatchlistViewModel
) {
    val scrollState = rememberScrollState()
    val color = MaterialTheme.colors.background

    var bannerAlpha = 1f.coerceAtMost(1 - (scrollState.value / 600f))

    ConstraintLayout(
        modifier = Modifier
            .verticalScroll(scrollState)
            .background(MaterialTheme.colors.background)
    ) {
        val (image, fade, description) = createRefs()
        Image(
            painter = rememberCoilPainter(request = movie.image),
            contentDescription = "",
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .constrainAs(image) { }
                .graphicsLayer {
                    alpha = bannerAlpha
                    translationY = -scrollState.value * 0.1f
                }
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, color),
                        startY = size.height * 0.6f,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.SrcOver)
                    }
                },
            contentScale = ContentScale.Crop,
        )


        Column(
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(image.bottom)
                }
        ) {
            MovieDescription(
                movieItem = movie,
                navController = navController,
                favoritesViewModel = favoritesViewModel,
                watchlistViewModel = watchlistViewModel
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(80.dp)
            .graphicsLayer {
                alpha = if (bannerAlpha < 0.05f) 1f else 0f
            }
            .drawWithCache {
                val gradient = Brush.verticalGradient(
                    colors = listOf(color, Color.Transparent),
                    startY = size.height * 0.5f,
                    endY = size.height
                )
                onDrawWithContent {
                    drawContent()
                    drawRect(gradient, blendMode = BlendMode.SrcOver)
                }
            }
    )

    val iconSecondaryColor = MaterialTheme.colors.primary
    Icon(
        imageVector = Icons.Rounded.ArrowBackIos,
        contentDescription = "back",
        tint = if (bannerAlpha == 1f) Color.White else iconSecondaryColor,
        modifier = Modifier
            .size(30.dp)
            .offset(16.dp, 16.dp)
            .clickable {
                navController.popBackStack()
            }
    )


    // Favourites Not implemented
    /*
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            imageVector = Icons.Rounded.FavoriteBorder,
            contentDescription = "favorite",
            tint = if (bannerAlpha == 1f) Color.White else iconSecondaryColor,
            modifier = Modifier
                .size(36.dp)
                .offset((-16).dp, 16.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
    }
    */
}


@Composable
fun MovieDetailStateWrapper(
    navController: NavController,
    movieItem: Resource<MovieItem>,
    favoritesViewModel: FavoritesViewModel,
    watchlistViewModel: WatchlistViewModel,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when (movieItem) {
        is Resource.Success -> {
            MovieDetail(
                navController = navController,
                movie = movieItem.data!!,
                favoritesViewModel = favoritesViewModel,
                watchlistViewModel = watchlistViewModel
            )
        }
        is Resource.Error -> {
            Text(
                text = movieItem.message!!,
                color = Color.Red,
                modifier = modifier
            )
        }
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = loadingModifier
            )
        }
    }
}
@Preview
@Composable
fun MovieDetailsScreenPreview() {
    val navController = rememberNavController()
    val movieId = "123" // Replace with your desired movie ID
    val viewModel: MovieDetailsViewModel = viewModel() // Provide your own ViewModel

    MovieDetailsScreen(navController = navController, movieId = movieId, viewModel = viewModel)
}

