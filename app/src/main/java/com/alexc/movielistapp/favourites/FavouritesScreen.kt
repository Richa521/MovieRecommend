package com.alexc.movielistapp.favourites

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alexc.movielistapp.core.bottombar.BottomBar
import com.alexc.movielistapp.core.movielist.MovieList
import com.alexc.movielistapp.repository.MovieRepository
import com.alexc.movielistapp.ui.theme.OpenSans


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable

fun FavoritesScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favourites",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary
                    )
                },
                backgroundColor = MaterialTheme.colors.surface,

                )
        },
        bottomBar = {
            BottomBar(navController)
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            // Favorites content
            MovieList(
                navController = navController,
                category = "Action" // Replace with your desired category
            )
        }
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen(navController = rememberNavController())
}
