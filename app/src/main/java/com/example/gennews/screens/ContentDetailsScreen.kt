package com.example.gennews.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gennews.presentation.viewmodel.NewsViewModel

@Composable
fun ContentDetailsScreen(newsViewModel: NewsViewModel) {
    val newsUiState by newsViewModel.newsUiState.collectAsState()
    Column(modifier = Modifier.padding(30.dp)) {
        newsUiState.selectedArticle?.content?.let { Text(it) }
        Spacer(modifier = Modifier.height(20.dp))
       AsyncImage(model = newsUiState.selectedArticle?.urlToImage, contentDescription = "News Image", modifier = Modifier.size(90.dp).clip(
            RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop)
    }
}