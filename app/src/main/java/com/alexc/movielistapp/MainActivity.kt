package com.alexc.movielistapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alexc.movielistapp.core.ForYou.ForYouScreen
import com.alexc.movielistapp.core.ForYou.ForYouViewModel
import com.alexc.movielistapp.core.homescreen.HomeScreen
import com.alexc.movielistapp.core.moviedetails.MovieDetailsScreen
import com.alexc.movielistapp.core.moviedetails.MovieDetailsViewModel
import com.alexc.movielistapp.core.movielist.MovieListScreen
import com.alexc.movielistapp.core.search.SearchScreen
import com.alexc.movielistapp.core.search.SearchViewModel
import com.alexc.movielistapp.favourites.FavoritesScreen
import com.alexc.movielistapp.core.settings.SettingsScreen
import com.alexc.movielistapp.core.watchlist.WatchlistScreen
import com.alexc.movielistapp.core.watchlist.WatchlistViewModel
import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.favourites.FavoritesViewModel
import com.alexc.movielistapp.favourites.PreferencesHelper
import com.alexc.movielistapp.repository.MovieRepository
import com.alexc.movielistapp.ui.theme.MovieListAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    var movie:MutableList<Result> = arrayListOf()
    @Inject
    lateinit var repository: MovieRepository

    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private val watchlistViewModel: WatchlistViewModel by viewModels()

     private val viewModel:MovieDetailsViewModel by viewModels()
    private lateinit var preferenceHelper: PreferencesHelper
    val mainScope = MainScope()
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeState.setIsDarkMode(this)

        preferenceHelper = PreferencesHelper(this) // Initialize PreferenceHelper

        setContent {
            MovieListAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home_screen") {
                    composable("home_screen") {
                        HomeScreen(navController)
                    }

                    composable(
                        "movie_details_screen/{movieId}",
                        arguments = listOf(
                            navArgument("movieId") {
                                type = NavType.StringType
                            }
                        )
                    ) { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getString("movieId")
                        MovieDetailsScreen(
                            navController = navController,
                            movieId = movieId ?: "",
                            viewModel=viewModel,
                            favoritesViewModel = favoritesViewModel,
                            watchlistViewModel = watchlistViewModel

                        )

                    }

                    composable(
                        "movie_list/{category}",
                        arguments = listOf(
                            navArgument("category") {
                                type = NavType.StringType
                            }
                        )
                    ) { backStackEntry ->
                        val category = backStackEntry.arguments?.getString("category")
                        MovieListScreen(
                            navController = navController,
                            category = category ?: "",
                        )
                    }

                    composable("search_screen") {
//                        val movieList = listOf("Phantom", "Flash", "Bahubali", "Spiderman", "Padmavati")
                        val viewModel: SearchViewModel = hiltViewModel()

                        SearchScreen(
                            navController = navController,
                            viewModel = viewModel
                        )

                    }

                    composable("settings_screen") {
                        SettingsScreen(navController)
                    }

                    composable("for_you_screen") { backStackEntry ->


                        val foryouviewModel:ForYouViewModel by viewModels()
                        ForYouScreen(navController = navController, viewModel = foryouviewModel, viewModel1 = favoritesViewModel)
                    }


                    composable("favorites_screen") {
                        FavoritesScreen(
                            navController = navController,

                            favoritesViewModel = favoritesViewModel,
                            preferenceHelper = preferenceHelper
                        )
                    }

                    composable("watchlist_screen") {
                        WatchlistScreen(
                            navController = navController,
                            watchlistViewModel = watchlistViewModel,
                            preferenceHelper = preferenceHelper
                        )
                    }
                }
            }
        }
    }
}
