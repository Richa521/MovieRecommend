package com.alexc.movielistapp.core.bottombar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        BottomBarItem.Home,
        BottomBarItem.Search,
        BottomBarItem.Favorites,
        BottomBarItem.Watchlist,
        BottomBarItem.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDest = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarItem,
    currentDest: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.title,
                modifier = Modifier.size(30.dp)
            )
        },


        selected = currentDest?.hierarchy?.any {
            it.route == screen.navigation
        } == true,
        onClick = {
            if (currentDest?.hierarchy?.any {
                    it.route == screen.navigation
                } != true) {
                navController.navigate(screen.navigation)
            }
        },
        selectedContentColor = MaterialTheme.colors.secondary,
        unselectedContentColor = MaterialTheme.colors.primary
    )
}


@Preview
@Composable
fun BottomBarPreview() {
    // Create a sample NavController instance (you can use a mock implementation)
    val navController = rememberNavController()

    // Call the original BottomBar composable
    BottomBar(navController = navController)
}

