package com.alexc.movielistapp

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ThemeState {
    var isDarkMode by mutableStateOf(false)

    fun setIsDarkMode(context: Context, isDarkMode: Boolean = getDarkMode(context)) {
        val sharedPreferences =
            context.getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("DarkMode", isDarkMode).apply();
        this.isDarkMode = isDarkMode
    }

    private fun getDarkMode(context: Context): Boolean {
        val sharedPreferences =
            context.getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        return sharedPreferences.getBoolean("DarkMode", false)
    }
}