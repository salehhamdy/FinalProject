package com.linkdev.finalproject.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.linkdev.finalproject.screens.CameraScreen
import com.linkdev.finalproject.screens.MovieDetailsScreen
import com.linkdev.finalproject.screens.MoviesScreen
import com.linkdev.finalproject.screens.PersonScreen
import com.linkdev.finalproject.viewmodel.MovieDetailsViewModel

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.MoviesScreen.route
    ) {
        composable(route = BottomBarScreen.MoviesScreen.route) {
            MoviesScreen(movieclick = { movieId -> navController.navigate("movie_details/$movieId") })
        }
        composable(route = BottomBarScreen.TvScreen.route) {
            PersonScreen()
        }
        composable(route = BottomBarScreen.SettingsScreen.route) {
            CameraScreen()
        }
        composable(route = Routes.DETAIL , arguments = listOf(navArgument("movieId") { type = NavType.IntType } )) {
            val id = it.arguments?.getInt("movieId")
            Log.d("movieid", "BottomNavGraph:$id ")
            val viewModel = hiltViewModel<MovieDetailsViewModel>()
            viewModel.fetchMovieDetails(id ?: 0)
            val state = viewModel.moviesState.value
            MovieDetailsScreen(moviedetails = state)
        }
    }
}