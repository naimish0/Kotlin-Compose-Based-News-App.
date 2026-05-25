package com.example.gennews.data.di.remote

import com.example.gennews.data.di.module.CategoryNewData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String
    ): Response<CategoryNewData>

}