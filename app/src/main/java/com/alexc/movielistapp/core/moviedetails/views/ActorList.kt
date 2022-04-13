package com.alexc.movielistapp.core.moviedetails.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alexc.movielistapp.data.models.Actor
import com.alexc.movielistapp.ui.theme.OpenSans
import com.google.accompanist.coil.rememberCoilPainter


@Composable
fun ActorsList(
    navController: NavController,
    actorsList: List<Actor> = arrayListOf(),
) {
    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 0.dp)
    ) {
        Text(
            "Top cast",
            fontSize = 20.sp,
            color = MaterialTheme.colors.primary,
            fontFamily = OpenSans,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 7.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyRow {
            items(actorsList.size) {
                ActorCard(actor = actorsList[it], navController = navController)
            }
        }
    }
}

@Composable
fun ActorCard(
    navController: NavController,
    actor: Actor,
) {

    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .width(60.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = rememberCoilPainter(request = actor.image),
            contentDescription = actor.name,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(50.dp)),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            actor.name,
            fontSize = 12.sp,
            fontFamily = OpenSans,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
        )

    }
}
