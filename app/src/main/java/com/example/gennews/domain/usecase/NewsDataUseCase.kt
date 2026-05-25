package com.example.gennews.domain.usecase

import com.example.gennews.data.di.module.CategoryNewData
import com.example.gennews.domain.repository.NewsRepository
import javax.inject.Inject

class NewsDataUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun invoke(query: String): Result<CategoryNewData> = newsRepository.getNewsData(query)
}