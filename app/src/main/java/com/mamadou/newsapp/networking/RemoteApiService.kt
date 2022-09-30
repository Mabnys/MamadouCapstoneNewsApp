package com.mamadou.newsapp.networking

import com.mamadou.newsapp.models.response.GetNewsResponse
import retrofit2.http.*

interface RemoteApiService {

    @GET("/v2/top-headlines?country=US")
   suspend fun getArticles(@Query("apiKey") token: String): GetNewsResponse
}