package com.example.gennews.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gennews.domain.newscatagory.NewsCategory
import com.example.gennews.presentation.viewmodel.NewsViewModel

@Composable
fun SplashScreen(newsViewModel: NewsViewModel, navController: NavController) {

    val newsUiState by newsViewModel.newsUiState.collectAsState()
    val isError = !newsUiState.error.isNullOrBlank()
    val indiaNews = newsUiState.categoryNewsMap[NewsCategory.INDIA]

    LaunchedEffect(indiaNews) {
        if (!indiaNews?.articles.isNullOrEmpty()) {
            moveToHomeScreen(navController)
        }
    }

    Column(
        modifier = Modifier.statusBarsPadding()
            .fillMaxSize()
            .background(if (isError) Color.White else Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ShowSplashOrErrorText(isError)

        Spacer(modifier = Modifier.height(24.dp))

        when {
            newsUiState.isLoading -> {
                Box(
                    modifier = Modifier.height(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

    }
}

@Composable
fun ShowSplashOrErrorText(isError: Boolean = false) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (isError)
                "Something Went Wrong"
            else
                "Gen News: Making Gen ",
            color = if (isError) Color.Red else Color.Black,
            fontFamily = FontFamily.SansSerif
        )
        if (!isError) {
            Text(
                text = "Genius",
                color = Color.White,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}


fun moveToHomeScreen(navController: NavController) {
    navController.navigate("home") {
        popUpTo("splash") {
            inclusive = true
        }
    }
}