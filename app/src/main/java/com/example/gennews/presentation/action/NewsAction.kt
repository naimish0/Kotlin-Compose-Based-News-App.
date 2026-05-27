package com.example.gennews.presentation.action

import com.example.gennews.domain.newscatagory.NewsCategory

sealed interface NewsAction {
     data class getNewsAction(val newsCategory: NewsCategory) : NewsAction
}