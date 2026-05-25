package com.example.gennews.domain.repository

import com.example.gennews.data.di.module.CategoryNewData

interface NewsRepository {
    suspend fun getNewsData(query: String) : Result<CategoryNewData>
}