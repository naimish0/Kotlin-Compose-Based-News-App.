package com.example.gennews.screens

import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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
                Text(category.displayName, color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            })
        }
    }
}