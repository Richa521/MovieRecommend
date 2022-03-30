package com.alexc.movielistapp.core.homescreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.movielistapp.core.homescreen.views.MostPopularMoviesWidget
import com.alexc.movielistapp.data.models.MovieListItem
import com.alexc.movielistapp.ui.theme.OpenSans
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


@Composable
fun HomeScreen(navController: NavController) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            item {
                MostPopularMoviesWidget(navController)
            }
            item {
                CategoriesWidget(listOf("Action", "Adventure", "Comedy"), navController)
            }
        }
    }
}


@Composable
fun CategoriesWidget(
    categories: List<String>,
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Column {

            Text(
                "Categories",
                fontSize = 23.sp,
                color = MaterialTheme.colors.primary,
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 7.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            FlowRow(
                mainAxisSize = SizeMode.Expand,
                mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
            ) {
                for (category in categories) {
                    CategoryItem(category = category, navController = navController)
                }
            }
        }
    }
}


@Composable
fun CategoryItem(category: String, navController: NavController) {
    val width = (LocalConfiguration.current.screenWidthDp.dp / 2) - 10.dp
    Box(
        modifier = Modifier
            .width(width)
            .aspectRatio(1.5f)
            .padding(start = 5.dp, end = 5.dp, bottom = 10.dp),
        contentAlignment = Center,
    ) {
        Box(contentAlignment = Center) {
            Image(
                painter = rememberCoilPainter(request = "https://4evaidrg3wj26w4ml1bpc9el-wpengine.netdna-ssl.com/wp-content/uploads/2018/07/12720668_web1_SKYSCRAPER_Movie_01-640x427.jpg"),
                contentDescription = "",
                modifier = Modifier
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
                category,
                fontSize = 23.sp,
                color = Color.White,
                fontFamily = OpenSans,
            )
        }
    }
}