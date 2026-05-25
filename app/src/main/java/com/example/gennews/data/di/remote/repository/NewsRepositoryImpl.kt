package com.example.gennews.data.di.remote.repository

import com.example.gennews.data.di.module.CategoryNewData
import com.example.gennews.data.di.remote.NewsDataSource
import com.example.gennews.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsDataSource: NewsDataSource) : NewsRepository {

    override suspend fun getNewsData(query: String): Result<CategoryNewData> {
        return newsDataSource.getNews(query)
    }

}