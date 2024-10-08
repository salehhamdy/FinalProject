package com.linkdev.finalproject.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Camera
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object MoviesScreen : BottomBarScreen(
        route = "MoviesScreen",
        title = "Movies",
        icon = Icons.Default.Movie
    )

    object TvScreen : BottomBarScreen(
        route = "PersonScreen",
        title = "Person",
        icon = Icons.Default.Person
    )

    object SettingsScreen : BottomBarScreen(
        route = "CameraScreen",
        title = "Camera",
        icon = Icons.Default.Camera
    )
}
