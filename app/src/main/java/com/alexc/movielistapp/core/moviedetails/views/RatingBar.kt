package com.alexc.movielistapp.core.moviedetails.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp


@Composable
fun RatingBar(
    rating: Float,
    modifier: Modifier = Modifier,
    color: Color = Color(0xFFD7AF53)
) {
    Row(modifier = modifier.wrapContentSize()) {
        (1..5).forEach { step ->
            val stepRating = when {
                rating > step -> 1f
                step.rem(rating) < 1 -> rating - (step - 1f)
                else -> 0f
            }
            RatingStar(stepRating, color)
        }
    }
}

@Composable
private fun RatingStar(
    rating: Float,
    ratingColor: Color = Color(0xFFD7AF53),
    backgroundColor: Color = Color.Gray
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .padding(5.dp)
            .clip(starShape)
    ) {
        Canvas(modifier = Modifier.size(maxHeight)) {
            drawRect(
                brush = SolidColor(backgroundColor),
                size = Size(
                    height = size.height * 1.4f,
                    width = size.width * 1.4f
                ),
                topLeft = Offset(
                    x = -(size.width * 0.1f),
                    y = -(size.height * 0.1f)
                )
            )
            if (rating > 0) {
                drawRect(
                    brush = SolidColor(ratingColor),
                    size = Size(
                        height = size.height * 1.1f,
                        width = size.width * rating
                    )
                )
            }
        }
    }
}

private val starShape = GenericShape { size, _ ->
    addPath(starPath(size.height))
}

private val starPath = { size: Float ->
    Path().apply {
        val outerRadius: Float = size / 1.8f
        val innerRadius: Double = outerRadius / 2.5
        var rot: Double = Math.PI / 2 * 3
        val cx: Float = size / 2
        val cy: Float = size / 20 * 11
        var x: Float = cx
        var y: Float = cy
        val step = Math.PI / 5

        moveTo(cx, cy - outerRadius)
        repeat(5) {
            x = (cx + Math.cos(rot) * outerRadius).toFloat()
            y = (cy + Math.sin(rot) * outerRadius).toFloat()
            lineTo(x, y)
            rot += step

            x = (cx + Math.cos(rot) * innerRadius).toFloat()
            y = (cy + Math.sin(rot) * innerRadius).toFloat()
            lineTo(x, y)
            rot += step
        }
        close()
    }
}

@Composable
fun RatingBarPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        RatingBar(
            3.8f,
            modifier = Modifier.height(20.dp)
        )
    }
}