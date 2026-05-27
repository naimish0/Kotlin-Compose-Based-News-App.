package com.example.gennews.screens

import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.gennews.domain.newscatagory.NewsCategory

@Composable
fun CategoryTab(
    selectedIndex: Int, categories: List<NewsCategory>, onTabSelected: (Int) -> Unit
) {
    ScrollableTabRow(selectedTabIndex = selectedIndex) {
        categories.forEachIndexed { index, category ->
            Tab(selected = selectedIndex == index, onClick = {
                onTabSelected(index)
            }, text = {
                Text(category.displayName)
            })
        }
    }
}