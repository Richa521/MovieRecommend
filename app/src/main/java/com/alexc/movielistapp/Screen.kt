package com.alexc.movielistapp

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object SearchScreen : Screen("search_screen")
    object FavoritesScreen : Screen("favorites_screen")
    object WatchlistScreen : Screen("watchlist_screen")
    object ForYouScreen : Screen("for_you_screen")
}
