package com.example.gennews.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gennews.presentation.viewmodel.NewsViewModel
import com.example.gennews.screens.ContentDetailsScreen
import com.example.gennews.screens.HomeScreen
import com.example.gennews.screens.SplashScreen

object NavHostObject {

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        val newsViewModel: NewsViewModel = hiltViewModel()
        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") {
                SplashScreen(
                    newsViewModel = newsViewModel,
                    navController = navController
                )
            }
            composable("home") {
                HomeScreen(newsViewModel, navController)
            }
            composable("contentDetails") {
                ContentDetailsScreen(newsViewModel)
            }
        }
    }
}