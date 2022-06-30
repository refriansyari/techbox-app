package com.refri.techbox.di

import com.refri.techbox.data.datasource.NewsListDataSource
import com.refri.techbox.data.service.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideNewsListDataSource(articleApiService: ApiServices): NewsListDataSource {
        return NewsListDataSource(articleApiService)
    }

}