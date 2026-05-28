package com.example.gennews.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gennews.data.di.module.Article
import com.example.gennews.data.di.module.CategoryNewData

@Composable
fun NewsListScreen(newsData: CategoryNewData, onNewsClick: (Article) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(newsData.articles.orEmpty()) { article ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                onClick = {
                    onNewsClick(article)
                }
            ) {
                Row(modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = article.title, modifier = Modifier
                            .weight(1f)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    AsyncImage(model = article.urlToImage, contentDescription = "News Image", modifier = Modifier.size(90.dp).clip(
                        RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop)
                }

            }

        }
    }
}