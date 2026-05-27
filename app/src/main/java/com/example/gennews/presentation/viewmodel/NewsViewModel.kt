package com.example.gennews.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gennews.domain.newscatagory.NewsCategory
import com.example.gennews.domain.usecase.NewsDataUseCase
import com.example.gennews.presentation.action.NewsAction
import com.example.gennews.presentation.intent.NewsIntent
import com.example.gennews.presentation.result.NewsResult
import com.example.gennews.presentation.uistate.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsDataUseCase: NewsDataUseCase) :
    ViewModel() {
    private val _newsUiState = MutableStateFlow(NewsUiState())
    val newsUiState = _newsUiState.asStateFlow()


    init {
        getNewsData(NewsIntent.getNewsIntent(NewsCategory.INDIA))
    }

    fun getNewsData(newsIntent: NewsIntent) {
        onIntentNews(newsIntent)
    }

    fun onIntentNews(newsIntent: NewsIntent) {
        when (newsIntent) {
            is NewsIntent.getNewsIntent -> handleAction(NewsAction.getNewsAction(newsIntent.newsCategory))
        }
    }

    fun handleAction(newsAction: NewsAction) {
        when (newsAction) {
            is NewsAction.getNewsAction -> viewModelScope.launch {
                handleNewsResult(NewsResult.Loading)
                newsDataUseCase.invoke(newsAction.newsCategory.query).onSuccess { response ->
                    Log.d("Response onSuccess: ", "${response.status}, ${response.totalResults}")
                    handleNewsResult(NewsResult.Success(newsAction.newsCategory, response))
                }.onFailure { exception ->
                    Log.d("Response onFailure: ", "${exception.message}")
                    handleNewsResult(NewsResult.Error(exception.message))
                }
            }
        }
    }

    fun handleNewsResult(newsResult: NewsResult) {
        when (newsResult) {
            is NewsResult.Loading -> _newsUiState.update { it.copy(isLoading = true) }
            is NewsResult.Success -> _newsUiState.update {
                it.copy(
                    isLoading = false,
                    error = null,
                    selectedCategory = newsResult.category,
                    categoryNewsMap = it.categoryNewsMap + (newsResult.category to newsResult.response)
                )
            }

            is NewsResult.Error -> _newsUiState.update {
                it.copy(
                    isLoading = false,
                    error = newsResult.message
                )
            }
        }
    }

}