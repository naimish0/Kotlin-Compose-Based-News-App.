package com.example.gennews.presentation.result

import com.example.gennews.data.di.module.CategoryNewData
import com.example.gennews.domain.newscatagory.NewsCategory

sealed interface NewsResult {
    data object Loading : NewsResult
    data class Error(val message: String?) : NewsResult
    data class Success(val category: NewsCategory, val response: CategoryNewData) : NewsResult
}