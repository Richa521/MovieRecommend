package com.alexc.movielistapp.core.watchlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alexc.movielistapp.core.bottombar.BottomBar
import com.alexc.movielistapp.core.homescreen.views.CategoriesWidget
import com.alexc.movielistapp.core.homescreen.views.InTheatersWidget
import com.alexc.movielistapp.core.homescreen.views.MostPopularMoviesWidget
import com.alexc.movielistapp.core.movielist.MovieList
import com.alexc.movielistapp.ui.theme.OpenSans

@Composable
fun WatchlistScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Watchlist",
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            // Favorites content
            MovieList(
                navController = navController,
                category = "Action" // Replace with your desired category
            )
        }
    }
}
