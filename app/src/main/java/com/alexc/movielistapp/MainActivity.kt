package com.alexc.movielistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alexc.movielistapp.core.homescreen.HomeScreen
import com.alexc.movielistapp.core.moviedetails.MovieDetailsScreen
import com.alexc.movielistapp.core.movielist.MovieListScreen
import com.alexc.movielistapp.core.search.SearchScreen
import com.alexc.movielistapp.repository.MovieRepository
import com.alexc.movielistapp.ui.theme.MovieListAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieListAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home_screen") {
                    composable("home_screen") {
                        HomeScreen(navController)
                    }

                    composable("movie_details_screen/{movieId}", arguments = listOf(
                        navArgument("movieId") {
                            type = NavType.StringType
                        }
                    )
                    ) {
                        val movieId = remember {
                            it.arguments?.getString("movieId")
                        }

                        MovieDetailsScreen(navController = navController, movieId = movieId ?: "")
                    }

                    composable("movie_list/{category}", arguments = listOf(
                        navArgument("category") {
                            type = NavType.StringType
                        }
                    )) {
                        val category = remember {
                            it.arguments?.getString("category")
                        }

                        MovieListScreen(navController = navController, category = category ?: "")
                    }

                    composable("search_screen") {
                        SearchScreen(navController = navController)
                    }

                    composable("favourites_screen") { }

                    composable("settings_screen") {}
                }
            }
        }
    }
}