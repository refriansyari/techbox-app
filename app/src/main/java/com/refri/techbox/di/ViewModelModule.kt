package com.refri.techbox.di

import com.refri.techbox.base.GenericViewModelFactory
import com.refri.techbox.ui.newslist.NewsListRepository
import com.refri.techbox.ui.newslist.NewsListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {
    @Provides
    @ActivityScoped
    fun provideNewsListViewModel(
       newsListRepository: NewsListRepository
    ): NewsListViewModel {
        return GenericViewModelFactory(NewsListViewModel(newsListRepository)).create(
            NewsListViewModel::class.java
        )
    }
}