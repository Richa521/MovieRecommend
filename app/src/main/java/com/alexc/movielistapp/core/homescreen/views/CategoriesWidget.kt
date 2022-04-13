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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alexc.movielistapp.data.models.CategoryListItem
import com.alexc.movielistapp.ui.theme.OpenSans
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

//TODO FIX
val categoriesList = arrayListOf<CategoryListItem>().apply {
    add(
        CategoryListItem(
            id = "id",
            "Action",
            "https://images7.alphacoders.com/855/thumb-1920-855790.jpg"
        )
    )
    add(
        CategoryListItem(
            id = "id",
            "Adventure",
            "https://www.magazine-hd.com/apps/wp/wp-content/uploads/2020/09/dune-destaque.jpg"
        )
    )
    add(
        CategoryListItem(
            id = "id",
            "Animation",
            "https://www.ixpaper.com/wp-content/uploads/2021/07/luca-wallpaper-ixpaper-2.jpg"
        )
    )
    // add(CategoryListItem(id = "id", "Crime", ""))
    //add(CategoryListItem(id = "id", "Drama", ""))
    add(
        CategoryListItem(
            id = "id",
            "Horror",
            "https://cm-santiago-do-cacem.pt/img/movies/89/what-order-you-should-watch-conjuring.jpg"
        )
    )
    // add(CategoryListItem(id = "id", "Romance", ""))
    // add(CategoryListItem(id = "id", "Sci-Fi", ""))
    // add(CategoryListItem(id = "id", "Thriller", ""))
}


@Composable
fun CategoriesWidget(
    categories: List<CategoryListItem> = categoriesList,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
    ) {

        Text(
            "Categories",
            fontSize = 23.sp,
            color = MaterialTheme.colors.primary,
            fontFamily = OpenSans,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 7.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

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


@Composable
fun CategoryItem(category: CategoryListItem, navController: NavController) {

    val width = (LocalConfiguration.current.screenWidthDp.dp / 2) - 10.dp

    Box(
        modifier = Modifier
            .width(width)
            .aspectRatio(1.5f)
            .padding(start = 5.dp, end = 5.dp, bottom = 10.dp)
            .clickable {
                navController.navigate(
                    "movie_list/${category.title.lowercase()}"
                )
            },
        contentAlignment = Alignment.Center,
    ) {

        val color = MaterialTheme.colors.primary

        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = rememberCoilPainter(request = category.image),
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, color),
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
                category.title,
                fontSize = 23.sp,
                color = Color.White,
                fontFamily = OpenSans,
            )
        }
    }
}