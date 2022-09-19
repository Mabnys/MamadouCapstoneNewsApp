package com.mamadou.newsapp.networking

import com.google.common.reflect.TypeToken
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.models.response.GetNewsResponse
import retrofit2.Call
import retrofit2.http.*

interface RemoteApiService {

    @GET("/v2/top-headlines")
    fun getArticles(@Query("apiKey") token: String): Call<GetNewsResponse>
}