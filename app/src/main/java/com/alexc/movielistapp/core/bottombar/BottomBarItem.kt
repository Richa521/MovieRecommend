package com.alexc.movielistapp.core.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(
    val navigation: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarItem(
        navigation = "home_screen",
        title = "Home",
        icon = Icons.Rounded.Home
    )

    object Search : BottomBarItem(
        navigation = "search_screen",
        title = "Search",
        icon = Icons.Rounded.Search
    )

    object Settings : BottomBarItem(
        navigation = "settings_screen",
        title = "Settings",
        icon = Icons.Rounded.Settings
    )
}