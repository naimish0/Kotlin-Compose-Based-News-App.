package com.example.gennews.data.di

import com.example.gennews.data.di.remote.ApiService
import com.example.gennews.data.di.remote.NewsDataSource
import com.example.gennews.data.di.remote.NewsDataSourceImpl
import com.example.gennews.data.di.remote.repository.NewsRepositoryImpl
import com.example.gennews.domain.repository.NewsRepository
import com.example.gennews.domain.usecase.NewsDataUseCase
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
       return Retrofit.Builder().baseUrl("https://newsapi.org/").client(client())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun client(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()

                val newUrl = original.url.newBuilder()
                    .addQueryParameter("apiKey", "f1a71a1602994188b62d75f2291a49e9")
                    .build()

                val request = original.newBuilder()
                    .url(newUrl)
                    .build()

                chain.proceed(request)
            }
            .build()
    }


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideNewsDataSourceImpl(apiService: ApiService): NewsDataSource {
        return NewsDataSourceImpl(apiService)
    }

    @Provides
    fun provideNewsRepositoryImpl(newsDataSource: NewsDataSource): NewsRepository {
        return NewsRepositoryImpl(newsDataSource)
    }

    @Provides
    fun provideNewsDataUseCase(newsRepository: NewsRepository): NewsDataUseCase {
        return NewsDataUseCase(newsRepository)
    }

}