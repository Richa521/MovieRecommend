package com.alexc.movielistapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    background = BackgroundColorDark,
    primary = PrimaryLight,
    secondary = SecondaryLight
)

private val LightColorPalette = lightColors(
    background = BackgroundColorLight,
    primary = PrimaryLight,
    secondary = SecondaryLight,
    surface = SurfaceLight
)

@Composable
fun MovieListAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}