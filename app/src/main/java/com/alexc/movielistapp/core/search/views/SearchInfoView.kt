package com.alexc.movielistapp.core.search.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alexc.movielistapp.ui.theme.OpenSans

@Composable
fun SearchInfoView(
    icon: ImageVector = Icons.Rounded.ErrorOutline,
    message: String = "Something went wrong",
    modifier: Modifier = Modifier
) {
    Column {
        Icon(
            imageVector = icon,
            contentDescription = message,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            message,
            fontFamily = OpenSans,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center
        )
    }
}
