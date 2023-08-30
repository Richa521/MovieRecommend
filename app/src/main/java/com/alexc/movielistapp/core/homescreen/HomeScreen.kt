package com.alexc.movielistapp.core.homescreen

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.movielistapp.core.ForYou.ForYouViewModel
import com.alexc.movielistapp.core.bottombar.BottomBar
import com.alexc.movielistapp.core.homescreen.views.CategoriesWidget
import com.alexc.movielistapp.core.homescreen.views.InTheatersWidget
import com.alexc.movielistapp.core.homescreen.views.MostPopularMoviesWidget
import com.alexc.movielistapp.core.homescreen.views.RecommendedMovieWidget
import com.alexc.movielistapp.core.moviedetails.MovieDetailsViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: ForYouViewModel) {

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = Modifier
            .fillMaxSize()
    ) {
        val context = LocalContext.current
        LazyColumn(
            contentPadding = PaddingValues(bottom = 50.dp) // Add some bottom padding
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                InTheatersWidget(navController)
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
            item {
                RecommendedMovieWidget(navController = navController, viewModel = viewModel, context = context)
            }
            item {
                MostPopularMoviesWidget(navController)
            }
            item {
                CategoriesWidget(navController = navController)
            }
            item {
                Spacer(modifier = Modifier.height(60.dp))
            }
            item {

                Text(
                    text = "Movie information provided by TMDB",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

            }
        }
    }
}
