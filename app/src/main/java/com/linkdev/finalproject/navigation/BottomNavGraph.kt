package com.linkdev.finalproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.linkdev.finalproject.screens.CameraScreen
import com.linkdev.finalproject.screens.MoviesScreen
import com.linkdev.finalproject.screens.PersonScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.MoviesScreen.route
    ) {
        composable(route = BottomBarScreen.MoviesScreen.route) {
            MoviesScreen()
        }
        composable(route = BottomBarScreen.TvScreen.route) {
            PersonScreen()
        }
        composable(route = BottomBarScreen.SettingsScreen.route) {
            CameraScreen()
        }
    }
}