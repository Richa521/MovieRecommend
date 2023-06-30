package com.alexc.movielistapp.core.homescreen.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.movielistapp.core.homescreen.HomeScreenViewModel
import com.alexc.movielistapp.ui.theme.OpenSans
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class)
@Composable
fun InTheatersWidget(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val movieList by remember { viewModel.inTheatersMovies }
    val isLoading by remember { viewModel.inTheatersIsLoading }


    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "In Theaters",
                fontSize = 23.sp,
                color = MaterialTheme.colors.primary,
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 17.dp)
            )

            IconButton(
                onClick = {
                    navController.navigate("settings_screen")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "For You"
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        HorizontalPager(
            count = movieList.size,
            contentPadding = PaddingValues(horizontal = 33.dp),
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Card(
                Modifier
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        // We animate the scaleX + scaleY, between 85% and 100%
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                // Card content
                val movie = movieList.get(page)

                // Get the correct image url
                //val imageSplitUrl = movie.image.split(".", limit = 4)
                //val imageUrl = movie.image.replace(imageSplitUrl[imageSplitUrl.size-1],"") + ".jpg"
               val imageUrl="https://image.tmdb.org/t/p/original"+movie.poster_path
                Box(modifier = Modifier.clickable {
                    navController.navigate(
                        "movie_details_screen/${movie.id}"
                    )
                }) {
                    Image(
                        painter = rememberCoilPainter(request = imageUrl),
                        contentDescription = "",
                        modifier = Modifier
                            .aspectRatio(1.5f, true)
                            .clip(RoundedCornerShape(10.dp))
                            .drawWithCache {
                                val gradient = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = size.height / 10,
                                    endY = size.height
                                )
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(gradient, blendMode = BlendMode.Multiply)
                                }
                            },
                        contentScale = ContentScale.Crop,
                    )

                    Text(
                        movie.title,
                        fontSize = 25.sp,
                        color = Color.White,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 15.dp)
                            .align(Alignment.BottomCenter),
                    )

                }
            }
        }
    }
}

fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}

