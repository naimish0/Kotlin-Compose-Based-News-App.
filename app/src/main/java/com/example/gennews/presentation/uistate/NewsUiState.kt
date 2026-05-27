package com.example.gennews.presentation.uistate

import com.example.gennews.data.di.module.CategoryNewData
import com.example.gennews.domain.newscatagory.NewsCategory

data class NewsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedCategory: NewsCategory = NewsCategory.INDIA,
    val categoryNewsMap: Map<NewsCategory, CategoryNewData> = emptyMap()
)