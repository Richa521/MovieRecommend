package com.alexc.movielistapp.core.homescreen.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alexc.movielistapp.data.model.Result
import com.alexc.movielistapp.ui.theme.OpenSans
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun MovieCard(
    movie: Result,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Box(
        modifier = modifier
            .width(150.dp)
            .clickable {
                navController.navigate(
                    "movie_details_screen/${movie.id}"
                )
            }
            .padding(10.dp),
    ) {
        // Get the correct image url
        val poster="https://image.tmdb.org/t/p/original"+movie.poster_path
        val imageSplitUrl =poster.split(".", limit = 4)
        val imageUrl = poster.replace(imageSplitUrl[imageSplitUrl.size-1],"") + ".jpg"

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberCoilPainter(request = poster),
                contentDescription = movie.title,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                movie.title,
                fontFamily = OpenSans,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center
            )
        }
    }
}
