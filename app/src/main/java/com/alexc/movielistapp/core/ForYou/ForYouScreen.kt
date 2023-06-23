package com.alexc.movielistapp.core.ForYou

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.movielistapp.core.bottombar.BottomBar
import com.alexc.movielistapp.core.watchlist.MovieListItem
import com.alexc.movielistapp.data.models.MovieListItem
import com.alexc.movielistapp.ui.theme.OpenSans
import com.google.accompanist.coil.rememberCoilPainter

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForYouScreen(
    navController: NavController
) {

        val nearestMovies = remember {
            val args = navController.currentBackStackEntry?.arguments
            val movieTitles = args?.getString("nearestMovies")?.split(",") ?: emptyList()
            movieTitles.map { Movie(it) }
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "For You",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary
                    )
                },
                backgroundColor = MaterialTheme.colors.surface,
            )
        },
        bottomBar = {
            BottomBar(navController)
        },
        modifier = Modifier.fillMaxSize()
    ) {
        if (nearestMovies.isNotEmpty()) {
            LazyColumn {
                items(nearestMovies) { movie ->
                    Text(text = movie.title)
                }

            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No movies available",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}
