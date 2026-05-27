package com.example.gennews.data.di.module

import androidx.compose.runtime.Immutable

@Immutable
data class CategoryNewData(
    val articles: List<Article>? = emptyList(), val status: String? = "", val totalResults: Int = 0
)