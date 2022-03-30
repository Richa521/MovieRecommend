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
                    }

                    composable("search_screen") { }

                    composable("favourites_screen") { }

                    composable("settings_screen") {}
                }
            }
        }
    }
}