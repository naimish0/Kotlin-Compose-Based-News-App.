package com.example.gennews.presentation.intent

import com.example.gennews.domain.newscatagory.NewsCategory

sealed interface NewsIntent {
    data class getNewsIntent(val newsCategory: NewsCategory) : NewsIntent
}