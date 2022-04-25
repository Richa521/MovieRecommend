package com.alexc.movielistapp.core.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alexc.movielistapp.ThemeState
import com.alexc.movielistapp.ui.theme.OpenSans


@Composable
fun SettingsScreen(
    navController: NavController,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Settings",
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
                        contentDescription = "back"
                    )
                }
            }
        )

        val context = LocalContext.current
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.DarkMode,
                contentDescription = "DarkMode",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .size(30.dp)
                    .align(alignment = CenterVertically),
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                modifier = Modifier
                    .weight(1f)
                    .align(alignment = CenterVertically),
                text = "Dark mode",
                fontFamily = OpenSans,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.primary
            )

            Spacer(modifier = Modifier.width(20.dp))

            Switch(checked = ThemeState.isDarkMode, onCheckedChange = {
                val theme = when(it){
                    true -> AppCompatDelegate.MODE_NIGHT_YES
                    false -> AppCompatDelegate.MODE_NIGHT_NO
                }
                AppCompatDelegate.setDefaultNightMode(theme)
                ThemeState.setIsDarkMode(context = context,it)
            }, colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.secondary
            ))
        }
    }
}