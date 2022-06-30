package com.refri.techbox.data.datasource

import com.refri.techbox.data.response.NewsListResponse
import com.refri.techbox.data.service.ApiServices

class NewsListDataSource(private val newsApiServices : ApiServices?) {

    suspend fun getNewsList() : NewsListResponse {
        return newsApiServices!!.getNewsList()
    }
}