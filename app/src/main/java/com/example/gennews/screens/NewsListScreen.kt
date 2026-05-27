package com.example.gennews.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gennews.data.di.module.CategoryNewData

@Composable
fun NewsListScreen(newsData: CategoryNewData) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(newsData.articles.orEmpty()) {article ->
            Row {
                Text(text = article.title,
                    modifier = Modifier.padding(16.dp))
            }

        }
    }
}