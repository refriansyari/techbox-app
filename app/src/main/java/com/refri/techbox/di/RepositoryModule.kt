package com.refri.techbox.di

import com.refri.techbox.data.datasource.NewsListDataSource
import com.refri.techbox.ui.newslist.NewsListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsListRepository(
        newsListDataSource: NewsListDataSource
    ) : NewsListRepository{
        return NewsListRepository(newsListDataSource)
    }
}