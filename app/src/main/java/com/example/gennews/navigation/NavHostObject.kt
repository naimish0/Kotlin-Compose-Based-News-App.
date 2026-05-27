package com.example.gennews.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gennews.presentation.viewmodel.NewsViewModel
import com.example.gennews.screens.HomeScreen
import com.example.gennews.screens.SplashScreen

object NavHostObject {

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") {
                val newsViewModel: NewsViewModel = hiltViewModel()
                SplashScreen(
                    newsViewModel = newsViewModel,
                    navController = navController
                )
            }
            composable("home") {
                val newsViewModel: NewsViewModel = hiltViewModel()
                HomeScreen(newsViewModel)
            }
        }
    }
}