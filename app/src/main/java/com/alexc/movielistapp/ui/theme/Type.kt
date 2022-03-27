package com.alexc.movielistapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alexc.movielistapp.R


val OpenSans = FontFamily(
        Font(R.font.open_sans_regular, FontWeight.Normal),
        Font(R.font.open_sans_light, FontWeight.Light),
        Font(R.font.open_sans_medium, FontWeight.Medium),
        Font(R.font.open_sans_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
        body1 = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        )
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)