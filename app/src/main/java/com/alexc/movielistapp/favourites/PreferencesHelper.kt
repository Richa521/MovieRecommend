package com.alexc.movielistapp.favourites


import com.alexc.movielistapp.data.models.MovieItem
import com.google.gson.reflect.TypeToken
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


class PreferencesHelper(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(
        "FavoritesPreferences",
        Context.MODE_PRIVATE
    )

    fun saveFavorites(favorites: List<MovieItem>) {
        val gson = Gson()
        val jsonFavorites = gson.toJson(favorites)
        preferences.edit().putString("favorites", jsonFavorites).apply()
    }

    fun getFavorites(): List<MovieItem> {
        val gson = Gson()
        val jsonFavorites = preferences.getString("favorites", null)
        return if (jsonFavorites != null) {
            gson.fromJson(jsonFavorites, Array<MovieItem>::class.java).toList()
        } else {
            emptyList()
        }
    }

    fun saveWatchlist(watchlist: List<MovieItem>) {
        val gson = Gson()
        val jsonWatchlist = gson.toJson(watchlist)
        preferences.edit().putString("watchlist", jsonWatchlist).apply()
    }

    fun getWatchlist(): List<MovieItem> {
        val gson = Gson()
        val jsonWatchlist = preferences.getString("watchlist", null)
        return if (jsonWatchlist != null) {
            gson.fromJson(jsonWatchlist, Array<MovieItem>::class.java).toList()
        } else {
            emptyList()
        }
    }
}