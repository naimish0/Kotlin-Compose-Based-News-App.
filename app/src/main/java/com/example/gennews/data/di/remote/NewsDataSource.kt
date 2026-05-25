package com.example.gennews.data.di.remote

import com.example.gennews.data.di.module.CategoryNewData

interface NewsDataSource {
    suspend fun getNews(query: String): Result<CategoryNewData>
}