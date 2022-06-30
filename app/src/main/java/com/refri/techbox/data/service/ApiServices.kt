package com.refri.techbox.data.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.refri.techbox.data.response.NewsListResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiServices {

    @GET("everything")
    suspend fun getNewsList(
        @Query("q") q: String = "technology",
        @Query("apiKey") key: String = "4d139b4f833d45de80804c72a67c9f9e"
    ) : NewsListResponse

//    everything?q=bitcoin&apiKey=4d139b4f833d45de80804c72a67c9f9e

    companion object {
        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor): ApiServices {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiServices::class.java)
        }
    }
}