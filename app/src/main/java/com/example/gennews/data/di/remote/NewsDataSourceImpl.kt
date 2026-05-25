package com.example.gennews.data.di.remote

import com.example.gennews.data.di.module.CategoryNewData
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(private val apiService: ApiService) : NewsDataSource {

    override suspend fun getNews(query: String): Result<CategoryNewData> {
        try {
            val response = apiService.getNews(query)
            if (response.isSuccessful) {
                val newsDto = response.body() ?: CategoryNewData()
                return Result.success(newsDto)
            } else return Result.failure(Exception("Something Went Wrong"))
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}