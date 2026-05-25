package com.example.gennews.data.di.module

data class CategoryNewData(
    val articles: List<Article>? = listOf(), val status: String? = "", val totalResults: Int = 0
)