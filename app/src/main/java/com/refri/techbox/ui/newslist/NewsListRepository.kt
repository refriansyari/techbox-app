package com.refri.techbox.ui.newslist

import com.refri.techbox.data.datasource.NewsListDataSource
import com.refri.techbox.data.response.NewsListResponse
import javax.inject.Inject

class NewsListRepository
    @Inject constructor (private val newsListDataSource: NewsListDataSource) {

    suspend fun getNewsList(): NewsListResponse{
        return newsListDataSource.getNewsList()
    }
}