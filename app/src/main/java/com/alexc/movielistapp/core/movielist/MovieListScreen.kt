package com.alexc.movielistapp.core.movielist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.alexc.movielistapp.ui.theme.OpenSans


@Composable
fun MovieListScreen(
    navController: NavController,
    category: String
) {
    var str="";
    if(category=="28")
        str="Action"
    else if(category=="12")
        str="Adventure"
    else if(category=="16")
        str="Animation"
    else if(category=="27")
        str="Horror"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {

        TopAppBar(
            title = {
                Text(
                    text = str.uppercase(),
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            },
            backgroundColor = MaterialTheme.colors.surface,
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIos,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = "back")
                }
            }
        )
        MovieList(navController = navController, category = category)
    }
}


