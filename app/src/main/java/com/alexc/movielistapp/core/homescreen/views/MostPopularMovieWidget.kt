package com.alexc.movielistapp.core.homescreen.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.movielistapp.core.homescreen.HomeScreenViewModel
import com.alexc.movielistapp.ui.theme.OpenSans

@Composable
fun MostPopularMoviesWidget(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val movieList by remember { viewModel.mostPopularMoviesList }
    val isLoading by remember { viewModel.mostPopularIsLoading }

    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 0.dp)
    ) {
        Text(
            "Most Popular",
            fontSize = 23.sp,
            color = MaterialTheme.colors.primary,
            fontFamily = OpenSans,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 7.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow {
            items(movieList.size) {
                MovieCard(movie = movieList[it], navController = navController)
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colors.primary)
            }
        }
    }
}
