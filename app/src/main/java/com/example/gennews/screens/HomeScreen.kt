package com.example.gennews.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gennews.domain.newscatagory.NewsCategory
import com.example.gennews.presentation.intent.NewsIntent
import com.example.gennews.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(newsViewModel: NewsViewModel, navController: NavController) {
    val newsUiState by newsViewModel.newsUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val categories = NewsCategory.entries
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { categories.size })
    LaunchedEffect(pagerState.currentPage) {
        val currentCategory = categories[pagerState.currentPage]
        newsViewModel.getNewsData(NewsIntent.getNewsIntent(currentCategory))
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()) {
        CategoryTab(
            selectedIndex = pagerState.currentPage,
            categories = categories,
            onTabSelected = { index ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            })

        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            val category = categories[page]
            val newsData = newsUiState.categoryNewsMap[category]
            when {
                newsUiState.isLoading && newsData == null -> {
                    LoadingScreen()
                }

                newsUiState.error != null && newsData == null -> {
                    ErrorScreen("Something Went Wrong")
                }

                newsData != null -> {
                    NewsListScreen(newsData, onNewsClick = { article ->
                        newsViewModel.onArticleSelected(article)
                        Log.d("Naimish", "VM = ${newsViewModel.hashCode()}")
                        navController.navigate("contentDetails")
                    })
                }
            }
        }
    }
}


//
//fun moveToContentDetailsScreen(navController: NavController) {
//    navController.navigate("contentDetails")
//}